<template>
  <div class="page">
    <!-- Header -->
    <el-row justify="space-between" align="middle" class="mb-4">
      <div>
        <h1>Danh sách sản phẩm</h1>
        <p class="desc">
          Manage your inventory, prices, and stock levels.
        </p>
      </div>

      <el-space>
        <el-button>
          <el-icon><Download /></el-icon>
          Export
        </el-button>

        <el-button
            type="primary"
            tag="router-link"
            to="/admin/products/add"
        >
          <el-icon><Plus /></el-icon>
          Add New Product
        </el-button>

      </el-space>
    </el-row>

    <!-- Filters -->
    <el-card class="mb-3" shadow="never">
      <el-row :gutter="12">
        <el-col :span="12">
          <el-input
              placeholder="Search by name, SKU..."
              prefix-icon="Search"
          />
        </el-col>

        <el-col :span="6">
          <el-select placeholder="Category" clearable>
            <el-option label="Áo" value="ao" />
          </el-select>
        </el-col>

        <el-col :span="6">
          <el-select placeholder="Status" clearable>
            <el-option label="Active" value="ACTIVE" />
            <el-option label="Low Stock" value="LOW" />
            <el-option label="Out of Stock" value="OUT" />
          </el-select>
        </el-col>
      </el-row>

      <el-space class="mt-3">
        <el-tag type="primary">
          All Products {{ totalElements }}
        </el-tag>
        <el-tag>Low Stock</el-tag>
        <el-tag type="danger">Out of Stock</el-tag>
      </el-space>
    </el-card>

    <!-- Table -->
    <el-card shadow="never">
      <el-table :data="products" style="width: 100%">
        <el-table-column type="selection" width="50" />

        <!-- Product -->
        <el-table-column label="PRODUCT">
          <template #default="{ row }">
            <div class="product-cell">
              <img
                  :src="getPrimaryImage(row.images)"
                  @error="onImageError"
              />
              <div>
                <strong>{{ row.name }}</strong>
                <div class="muted">
                  {{ row.variants.length }} Variants
                </div>
              </div>
            </div>
          </template>
        </el-table-column>

        <el-table-column
            label="SKU"
            prop="productCode"
            width="150"
        />

        <el-table-column
            label="CATEGORY"
            prop="categoryName"
            width="120"
        />

        <!-- Stock -->
        <el-table-column label="STOCK" width="180">
          <template #default="{ row }">
            <el-progress
                :percentage="stockPercent(row)"
                :stroke-width="6"
                :show-text="false"
            />
            <span class="muted">
              {{ totalStock(row) }}
            </span>
          </template>
        </el-table-column>

        <!-- Price -->
        <el-table-column label="PRICE" width="120">
          <template #default="{ row }">
            <strong>
              {{ formatPrice(minSalePrice(row.variants)) }}
            </strong>
          </template>
        </el-table-column>

        <!-- Status -->
        <el-table-column label="STATUS" width="120">
          <template #default="{ row }">
            <el-tag :type="statusType(row)">
              {{ statusText(row) }}
            </el-tag>
          </template>
        </el-table-column>

        <!-- Actions -->
        <el-table-column label="ACTIONS" width="80">
          <template #default>
            <el-dropdown>
              <span class="action-dots">⋮</span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>Edit</el-dropdown-item>
                  <el-dropdown-item>Delete</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </template>
        </el-table-column>
      </el-table>

      <!-- Pagination -->
      <el-pagination
          class="mt-4"
          background
          layout="prev, pager, next"
          :total="totalElements"
          :page-size="size"
          :current-page="page + 1"
          @current-change="onPageChange"
      />
    </el-card>
  </div>
</template>
<script setup>
import { ref, onMounted } from "vue";
import { getProducts } from "@/services/product.service.js";
import {
  Plus,
  Download,
  Search
} from "@element-plus/icons-vue";

const products = ref([]);
const page = ref(0);
const size = ref(5);
const totalPages = ref(0);
const totalElements = ref(0);

const loadProducts = async () => {
  const res = await getProducts(page.value, size.value);
  products.value = res.data.content;
  totalPages.value = res.data.totalPages;
  totalElements.value = res.data.totalElements;
};

onMounted(loadProducts);

const onPageChange = (p) => {
  page.value = p - 1;
  loadProducts();
};

/* ===== Helpers ===== */

const normalizeImageUrl = (url) => {
  if (!url) return "/no-image.png";
  if (url.includes("localhost:9000")) {
    return `/images/${url.split("/").pop()}`;
  }
  return url;
};

const getPrimaryImage = (images) => {
  if (!images?.length) return "/no-image.png";
  const primary = images.find((i) => i.isPrimary);
  return normalizeImageUrl(
      primary ? primary.imageUrl : images[0].imageUrl
  );
};

const onImageError = (e) => {
  e.target.src = "/no-image.png";
};

const totalStock = (p) =>
    p.variants.reduce((s, v) => s + v.quantity, 0);

const stockPercent = (p) =>
    Math.min(100, totalStock(p));

const minSalePrice = (variants) =>
    Math.min(...variants.map((v) => v.salePrice));

const statusText = (p) => {
  if (totalStock(p) === 0) return "Out of Stock";
  if (totalStock(p) < 20) return "Low Stock";
  return "Active";
};

const statusType = (p) => {
  if (totalStock(p) === 0) return "danger";
  if (totalStock(p) < 20) return "warning";
  return "success";
};

const formatPrice = (price) =>
    new Intl.NumberFormat("vi-VN").format(price) + "đ";
</script>
<style scoped>
.page {
  padding: 24px;
}

.desc {
  font-size: 14px;
  color: #6b7280;
}

.product-cell {
  display: flex;
  gap: 12px;
  align-items: center;
}

.product-cell img {
  width: 40px;
  height: 40px;
  border-radius: 8px;
  object-fit: cover;
}

.muted {
  font-size: 12px;
  color: #6b7280;
}

.action-dots {
  cursor: pointer;
  font-size: 18px;
}
</style>
