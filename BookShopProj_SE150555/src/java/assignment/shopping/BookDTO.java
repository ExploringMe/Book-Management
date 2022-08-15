package assignment.shopping;

public class BookDTO {

    private String productID;
    private String productName;
    private String description;
    private String categoryName;
    private double productPrice;
    private int productQuantity;
    private int orderQuantity;
    private String image;
    private String orderID;
    private String detailID;
    private String orderDate;
    private String userID;
    private String address;
    private String phone;
    private double total;

    public BookDTO() {
        this.productID = "";
        this.productName = "";
        this.description = "";
        this.categoryName = "";
        this.productPrice = 0;
        this.productQuantity = 0;
        this.orderQuantity = 0;
        this.image = "";
        this.orderID = "";
        this.detailID = "";
        this.orderDate = "";
        this.userID = "";
        this.address = "";
        this.phone = "";
        this.total = 0;
    }

    public BookDTO(String productID, String productName, double productPrice, int orderQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderQuantity = orderQuantity;
    }

    public BookDTO(String orderID, String orderDate, String userID, String address, String phone, double total) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.userID = userID;
        this.address = address;
        this.phone = phone;
        this.total = total;
    }

    public BookDTO(String productID, double productPrice, int orderQuantity, String orderID, String detailID, String orderDate) {
        this.productID = productID;
        this.productPrice = productPrice;
        this.orderQuantity = orderQuantity;
        this.orderID = orderID;
        this.detailID = detailID;
        this.orderDate = orderDate;
    }

    public BookDTO(String productID, double productPrice, int orderQuantity, String orderID, String detailID) {
        this.productID = productID;
        this.productPrice = productPrice;
        this.orderQuantity = orderQuantity;
        this.orderID = orderID;
        this.detailID = detailID;
    }

    public BookDTO(String productID, String productName, String description, String categoryName, double productPrice, int productQuantity, String image) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.image = image;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return categoryName;
    }

    public void setCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
