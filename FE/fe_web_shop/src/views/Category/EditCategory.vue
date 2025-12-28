<template>
  <div class="category-form">
    <h1>Cập Nhật Danh Mục</h1>

    <form @submit.prevent="update">
      <label>Tên danh mục</label>
      <input v-model="category.name" required />

      <label>Mô tả</label>
      <textarea v-model="category.description"></textarea>

      <button type="submit">Cập Nhật</button>
    </form>
  </div>
</template>

<script>
import { getCategoryById, updateCategory } from "@/services/category.service";

export default {
  data() {
    return {
      category: {
        id: null,
        name: "",
        description: "",
      },
    };
  },
  async mounted() {
    const id = this.$route.params.id;
    const res = await getCategoryById(id);
    this.category = res.data;
  },
  methods: {
    async update() {
      await updateCategory(this.category.id, this.category);
      this.$router.push({ name: "Categories" });
    },
  },
};
</script>

<style scoped>
.category-form {
  max-width: 500px;
}

form {
  display: flex;
  flex-direction: column;
}

input, textarea {
  margin-bottom: 12px;
  padding: 8px;
}

button {
  padding: 10px;
  background: #28a745;
  color: white;
  border: none;
}
</style>
