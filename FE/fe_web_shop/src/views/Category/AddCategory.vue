<template>
  <div class="category-form">
    <h1>➕ Thêm Danh Mục</h1>

    <form @submit.prevent="submit">
      <div class="form-group">
        <label>Tên danh mục</label>
        <input
            type="text"
            v-model="category.name"
            placeholder="Nhập tên danh mục"
            required
        />
      </div>

      <div class="form-group">
        <label>Mô tả</label>
        <textarea
            v-model="category.description"
            placeholder="Mô tả danh mục"
        ></textarea>
      </div>

      <div class="actions">
        <button type="submit">💾 Lưu</button>
        <button type="button" @click="back">↩ Quay lại</button>
      </div>
    </form>
  </div>
</template>

<script>
import { createCategory } from "@/services/category.service";

export default {
  data() {
    return {
      category: {
        name: "",
        description: "",
      },
    };
  },
  methods: {
    async submit() {
      try {
        await createCategory(this.category);
        alert("Thêm danh mục thành công");
        this.$router.push({ name: "Categories" });
      } catch (e) {
        console.error(e);
        alert("Thêm thất bại");
      }
    },
    back() {
      this.$router.push({ name: "Categories" });
    },
  },
};
</script>

<style scoped>
.category-form {
  max-width: 500px;
}

.form-group {
  margin-bottom: 15px;
}

input,
textarea {
  width: 100%;
  padding: 8px;
}

.actions {
  display: flex;
  gap: 10px;
}

button {
  padding: 8px 14px;
  border: none;
  cursor: pointer;
}

button[type="submit"] {
  background: #007bff;
  color: white;
}

button[type="button"] {
  background: #6c757d;
  color: white;
}
</style>
