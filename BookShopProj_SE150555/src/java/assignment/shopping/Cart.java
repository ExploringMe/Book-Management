package assignment.shopping;

import assignment.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<String, BookDTO> cart;

    public Cart() {
    }

    public Cart(Map<String, BookDTO> cart) {
        this.cart = cart;
    }

    public Map<String, BookDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, BookDTO> cart) {
        this.cart = cart;
    }

    public boolean add(BookDTO book) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(book.getProductID())) {
            int currentQuantity = this.cart.get(book.getProductID()).getOrderQuantity();
            book.setOrderQuantity(currentQuantity + book.getOrderQuantity());
        }
        cart.put(book.getProductID(), book);
        return true;
    }

    public void delete(String productID) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(productID)) {
            this.cart.remove(productID);
        }
    }

    public void update(String productID, BookDTO newOrder) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(productID)) {
            this.cart.replace(productID, newOrder);
        }
    }

    public int getCurrentQuantity(String productID) throws SQLException {
        int productQuantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productQuantity FROM tblProducts WHERE productID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    productQuantity = rs.getInt("productQuantity");
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return productQuantity;
    }

    public boolean insertDetail(BookDTO book) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblOrderDetail(orderDetailID, orderID, productID, orderQuantity, orderPrice, statusID) "
                        + "VALUES(?,?,?,?,?,1) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, book.getDetailID());
                stm.setString(2, book.getOrderID());
                stm.setString(3, book.getProductID());
                stm.setInt(4, book.getOrderQuantity());
                stm.setDouble(5, book.getProductPrice());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public void updateQuantity(BookDTO book) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblProducts "
                        + "SET productQuantity-=? "
                        + "WHERE productID=? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, book.getOrderQuantity());
                stm.setString(2, book.getProductID());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean insertOrder(BookDTO book) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblOrders(orderID, userID, address, phone, totalMoney, orderDate, statusID) "
                        + "VALUES(?,?,?,?,?,?,1) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, book.getOrderID());
                stm.setString(2, book.getUserID());
                stm.setString(3, book.getAddress());
                stm.setString(4, book.getPhone());
                stm.setDouble(5, book.getTotal());
                stm.setString(6, book.getOrderDate());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
