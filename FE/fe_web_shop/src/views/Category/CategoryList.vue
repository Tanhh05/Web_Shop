<template>
  <div class="category-management">
    <h1>Danh Mục Sản Phẩm</h1>

    <div class="actions">
      <router-link :to="{ name: 'AddCategory' }">
        <button class="btn-primary">+ Thêm Danh Mục</button>
      </router-link>
    </div>

    <table>
      <thead>
      <tr>
        <th>Tên Danh Mục</th>
        <th>Mô Tả</th>
        <th>Hành Động</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="c in categories" :key="c.id">
        <td>{{ c.name }}</td>
        <td>{{ c.description }}</td>
        <td>
          <router-link
              :to="{ name: 'EditCategory', params: { id: c.id } }"
          >
            <button class="btn-edit">Sửa</button>
          </router-link>
          <button class="btn-delete" @click="remove(c.id)">Xóa</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { getCategories, deleteCategory } from "@/services/category.service";

export default {
  data() {
    return {
      categories: [],
    };
  },
  mounted() {
    this.fetchCategories();
  },
  methods: {
    async fetchCategories() {
      const res = await getCategories();
      this.categories = res.data;
    },
    async remove(id) {
      if (confirm("Bạn có chắc muốn xóa danh mục này?")) {
        await deleteCategory(id);
        this.fetchCategories();
      }
    },
  },
};
</script>

<style scoped>
.category-management {
  padding: 20px;
}

.actions {
  margin-bottom: 15px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background: #f4f6f8;
}

th, td {
  padding: 12px;
  border: 1px solid #ddd;
}

button {
  padding: 6px 12px;
  margin-right: 6px;
}

.btn-primary {
  background: #007bff;
  color: white;
  border: none;
}

.btn-edit {
  background: #ffc107;
  border: none;
}

.btn-delete {
  background: #dc3545;
  color: white;
  border: none;
}
</style>
