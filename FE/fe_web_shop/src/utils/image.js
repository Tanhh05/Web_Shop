export const getImageUrl = (image) => {
    if (!image) return "/no-image.png";

    if (image.startsWith("http")) return image;

    return `http://localhost/minio/${image}`;
};