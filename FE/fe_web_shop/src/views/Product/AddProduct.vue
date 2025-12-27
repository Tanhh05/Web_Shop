<template>
  <div class="page">
    <!-- Breadcrumb -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>Sản phẩm</el-breadcrumb-item>
      <el-breadcrumb-item>Thêm sản phẩm</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- Header -->
    <div class="header">
      <div>
        <h1>Thêm Sản Phẩm Mới</h1>
        <p class="desc">
          Điền thông tin chi tiết bên dưới để tạo sản phẩm mới trong kho.
        </p>
      </div>

      <el-space>
        <el-button>Hủy bỏ</el-button>
        <el-button type="primary" @click="submit">
          Lưu sản phẩm
        </el-button>
      </el-space>
    </div>

    <el-row :gutter="20">
      <!-- LEFT -->
      <el-col :span="16">
        <!-- Thông tin chung -->
        <el-card shadow="never" class="mb">
          <template #header>Thông tin chung</template>

          <el-form label-position="top">
            <el-form-item label="Tên sản phẩm">
              <el-input
                  v-model="product.name"
                  placeholder="VD: Áo thun nam Cotton Basic"
              />
            </el-form-item>

            <el-form-item label="Mô tả sản phẩm">
              <el-input
                  v-model="product.description"
                  type="textarea"
                  :rows="4"
                  placeholder="Nhập mô tả chi tiết về chất liệu, kiểu dáng..."
              />
            </el-form-item>
          </el-form>
        </el-card>

        <!-- Giá & kho -->
        <el-card shadow="never" class="mb">
          <template #header>Giá bán & Kho hàng</template>

          <el-row :gutter="12">
            <el-col :span="12">
              <el-form-item label="Giá bán (VND)">
                <el-input-number
                    v-model="price.sale"
                    :min="0"
                    style="width:100%"
                />
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="Giá gốc (VND)">
                <el-input-number
                    v-model="price.original"
                    :min="0"
                    style="width:100%"
                />
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="Mã sản phẩm (SKU)">
                <el-input v-model="product.productCode" />
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="Số lượng tồn kho">
                <el-input-number
                    v-model="stock"
                    :min="0"
                    style="width:100%"
                />
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>

        <!-- Variants -->
        <el-card shadow="never">
          <template #header>
            Variants
            <el-button
                type="primary"
                link
                @click="addVariant"
            >
              + Thêm variant
            </el-button>
          </template>

          <el-table :data="product.variants" border>
            <el-table-column prop="sku" label="SKU" />
            <el-table-column prop="color" label="Màu" />
            <el-table-column prop="size" label="Size" />
            <el-table-column prop="salePrice" label="Giá bán" />
            <el-table-column prop="quantity" label="SL" />

            <el-table-column width="60">
              <template #default="{ $index }">
                <el-button
                    type="danger"
                    size="small"
                    @click="removeVariant($index)"
                >
                  X
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>

      <!-- RIGHT -->
      <el-col :span="8">
        <!-- Images -->
        <el-card shadow="never" class="mb">
          <template #header>
            Hình ảnh
            <el-button type="primary" link>
              Thêm từ URL
            </el-button>
          </template>

          <el-upload
              drag
              multiple
              :auto-upload="false"
              :on-change="onFileChange"
          >
            <el-icon><UploadFilled /></el-icon>
            <p>Bấm để tải lên</p>
          </el-upload>
        </el-card>

        <!-- Classification -->
        <el-card shadow="never">
          <template #header>Phân loại</template>

          <el-form label-position="top">
            <el-form-item label="Trạng thái">
              <el-select v-model="product.status">
                <el-option label="Đang bán" value="ACTIVE" />
                <el-option label="Ẩn" value="INACTIVE" />
              </el-select>
            </el-form-item>

            <el-form-item label="Danh mục">
              <el-input-number
                  v-model="product.categoryId"
                  placeholder="Category ID"
                  style="width:100%"
              />
            </el-form-item>

            <el-form-item label="Thẻ (Tags)">
              <el-select multiple>
                <el-option label="New Arrival" />
                <el-option label="Summer" />
              </el-select>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import { ref } from "vue";
import http from "@/api/http";
import { UploadFilled } from "@element-plus/icons-vue";

const product = ref({
  productCode: "",
  name: "",
  description: "",
  status: "ACTIVE",
  categoryId: null,
  variants: [],
  images: [],
});

const images = ref([]);
const price = ref({ sale: 0, original: 0 });
const stock = ref(0);

const addVariant = () => {
  product.value.variants.push({
    sku: "",
    color: "",
    size: "",
    originalPrice: price.value.original,
    salePrice: price.value.sale,
    quantity: stock.value,
  });
};

const removeVariant = (index) => {
  product.value.variants.splice(index, 1);
};

const onFileChange = (file) => {
  images.value.push({
    file: file.raw,
    color: "",
    isPrimary: images.value.length === 0,
  });
};

const submit = async () => {
  const formData = new FormData();

  product.value.images = images.value.map((i) => ({
    color: i.color,
    isPrimary: i.isPrimary,
  }));

  formData.append("product", JSON.stringify(product.value));

  images.value.forEach((i) => {
    formData.append("images", i.file);
  });

  await http.post("/products", formData);
  alert("Thêm sản phẩm thành công!");
};
</script>
<style scoped>
.page {
  padding: 24px;
}

.header {
  display: flex;
  justify-content: space-between;
  margin: 16px 0 24px;
}

.desc {
  color: #6b7280;
  font-size: 14px;
}

.mb {
  margin-bottom: 20px;
}
</style>
