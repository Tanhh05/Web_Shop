<template>
  <el-aside width="220px" class="sidebar">
    <div class="logo">
      <h3>Admin Fashion</h3>
      <span>Quản lý cửa hàng</span>
    </div>

    <el-menu
        :default-active="activeMenu"
        router
        class="el-menu-vertical"
        background-color="transparent"
        text-color="#4b5960"
        active-text-color="#409eff"
    >
      <el-menu-item
          v-for="item in menuItems"
          :key="item.route"
          :index="item.route"
      >
        <el-icon>
          <component :is="Icons[item.icon]" />
        </el-icon>
        <span>{{ item.label }}</span>
      </el-menu-item>
    </el-menu>

    <!--    <div class="profile">-->
    <!--      <el-avatar size="36">NV</el-avatar>-->
    <!--      <div class="profile-info">-->
    <!--        <div class="name">Nguyễn Văn A</div>-->
    <!--        <div class="role">Quản trị viên</div>-->
    <!--      </div>-->
    <!--    </div>-->

    <div class="logout">
      <el-button type="text" plain block>
        Đăng xuất
      </el-button>
    </div>
  </el-aside>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { menuItems } from '@/utils/menu'

import * as Icons from '@element-plus/icons-vue'

const route = useRoute()

const activeMenu = computed(() => route.path)
</script>

<style scoped>
.sidebar {
  height: 100vh;
  background: #ffffff;
  border-right: 1px solid #eef2f6;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.logo {
  padding: 20px;
  border-bottom: 1px solid #f1f5f9;
}

.logo h3 {
  margin: 0;
  color: #409eff;
  font-size: 18px;
}

.logo span {
  font-size: 12px;
  color: #909399;
}

/* menu item styles */
.el-menu-vertical {
  border-right: none;
  padding-top: 12px;
  flex: 1;
}

.el-menu-vertical .el-menu-item {
  padding: 12px 20px;
  border-radius: 8px;
  margin: 6px 10px;
}

.el-menu-vertical .el-menu-item:hover {
  background: #f5fbff;
}

/* profile block */
.profile {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 14px 16px;
  border-top: 1px solid #f1f5f9;
  background: #fff;
}

.profile-info .name {
  font-weight: 600;
  font-size: 13px;
}

.profile-info .role {
  font-size: 12px;
  color: #909399;
}

/* logout area */
.logout {
  padding: 12px;
  border-top: 1px solid #f1f5f9;
}
</style>