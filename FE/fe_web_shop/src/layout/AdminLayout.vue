<template>
  <el-container style="height: 100vh">
    <Sidebar />

    <el-container>
      <el-header class="header">
        <el-input
            placeholder="Tìm kiếm đơn hàng, sản phẩm..."
            style="width: 300px"
        />

      </el-header>

      <el-main class="content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import Sidebar from '@/components/Sidebar.vue'
</script>

<style scoped>
.header {
  background: #fff;
  border-bottom: 1px solid #eee;
  display: flex;
  align-items: center;
  padding: 0 20px;
}

.role {
  font-weight: 400;
  font-size: 12px;
  color: #909399;
}

/* adjust dropdown arrow icon */
.arrow-icon {
  color: #c0c4cc;
}

/* content area */
.content {
  background: #f5f7fa;
  padding: 20px;
}
</style>