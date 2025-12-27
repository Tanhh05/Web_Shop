<template>
  <el-card>
    <h2 style="margin-bottom: 16px">Danh sách sản phẩm</h2>

    <el-table :data="products" border stripe>
      <el-table-column type="index" width="50" label="#" />

      <el-table-column prop="productCode" label="Mã SP" width="120" />

      <el-table-column prop="name" label="Tên sản phẩm" width="180" />

      <!-- Ảnh -->
      <el-table-column label="Ảnh" width="120">
        <template #default="{ row }">
          <el-image
              v-if="getMainImage(row)"
              :src="getMainImage(row)"
              style="width: 80px; height: 80px"
              fit="cover"
              :preview-src-list="[getMainImage(row)]"
          />
          <span v-else>Không có ảnh</span>
        </template>
      </el-table-column>

      <el-table-column prop="categoryName" label="Danh mục" width="120" />

      <!-- SKU -->
      <el-table-column label="SKU" width="160">
        <template #default="{ row }">
          {{ row.variants?.[0]?.sku || '-' }}
        </template>
      </el-table-column>

      <!-- Màu -->
      <el-table-column label="Màu" width="100">
        <template #default="{ row }">
          {{ row.variants?.[0]?.color || '-' }}
        </template>
      </el-table-column>

      <!-- Size -->
      <el-table-column label="Size" width="80">
        <template #default="{ row }">
          {{ row.variants?.[0]?.size || '-' }}
        </template>
      </el-table-column>

      <!-- Giá gốc -->
      <el-table-column label="Giá gốc" width="120">
        <template #default="{ row }">
          {{
            row.variants?.[0]?.originalPrice
                ? row.variants[0].originalPrice.toLocaleString() + ' đ'
                : '-'
          }}
        </template>
      </el-table-column>

      <!-- Giá sale -->
      <el-table-column label="Giá sale" width="120">
        <template #default="{ row }">
          {{
            row.variants?.[0]?.salePrice
                ? row.variants[0].salePrice.toLocaleString() + ' đ'
                : '-'
          }}
        </template>
      </el-table-column>

      <!-- Số lượng -->
      <el-table-column label="SL" width="80">
        <template #default="{ row }">
          {{ row.variants?.[0]?.quantity ?? 0 }}
        </template>
      </el-table-column>

      <!-- Trạng thái -->
      <el-table-column label="Trạng thái" width="120">
        <template #default="{ row }">
          <el-tag :type="row.status === 'ACTIVE' ? 'success' : 'danger'">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
    </el-table>

    <!-- Pagination -->
    <div style="margin-top: 20px; text-align: right">
      <el-pagination
          background
          layout="prev, pager, next"
          :current-page="page"
          :page-size="size"
          :total="total"
          @current-change="changePage"
      />
    </div>
  </el-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { getProductPage } from '@/services/product.service'

const products = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(5)

const BASE_IMAGE_URL = 'http://localhost:9000/'

// lấy ảnh primary
const getMainImage = (product) => {
  if (!product.images?.length) return null

  const img = product.images.find(i => i.isPrimary) || product.images[0]

  if (!img?.imageUrl) return null
  return img.imageUrl.startsWith('http')
      ? img.imageUrl
      : BASE_IMAGE_URL + img.imageUrl
}

const loadData = async () => {
  const res = await getProductPage(page.value - 1, size.value)
  products.value = res.content
  total.value = res.totalElements
}

const changePage = (p) => {
  page.value = p
  loadData()
}

onMounted(loadData)
</script>
