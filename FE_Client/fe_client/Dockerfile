# Sử dụng image node:22-alpine để giảm thiểu kích thước của Docker image
FROM node:22-alpine AS build

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Cài đặt các phụ thuộc hệ thống cần thiết cho npm
RUN apk add --no-cache \
  python3 \
  g++ \
  make \
  bash

# Sao chép package.json và package-lock.json để cài đặt các phụ thuộc npm
COPY package.json package-lock.json ./

# Cài đặt các phụ thuộc npm (bao gồm các dependencies cần thiết cho build)
RUN npm install --legacy-peer-deps

# Sao chép phần còn lại của mã nguồn vào container
COPY . .

# Expose cổng ứng dụng để truy cập từ bên ngoài container
EXPOSE 8082

# Chạy ứng dụng khi container khởi động
CMD ["npm", "run", "dev", "--", "--host"]
