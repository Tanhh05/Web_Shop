import http from "@/api/http";

export const getProducts = (page = 0, size = 5) => {
    return http.get("/products", {
        params: { page, size },
    });
};