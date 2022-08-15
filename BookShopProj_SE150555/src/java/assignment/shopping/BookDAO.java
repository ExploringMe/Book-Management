package assignment.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import assignment.utils.DBUtils;

public class BookDAO {

    public List<BookDTO> getListBook(String search) throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productID, productName, description, categoryName, productPrice, productQuantity, image, statusID "
                        + "FROM tblProducts, tblCategory "
                        + "WHERE productName like ? AND tblProducts.categoryID=tblCategory.categoryID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    String categoryName = rs.getString("categoryName");
                    Double productPrice = Double.parseDouble(rs.getString("productPrice"));
                    int productQuantity = Integer.parseInt(rs.getString("productQuantity"));
                    String image = rs.getString("image");
                    String statusID = rs.getString("statusID");

                    if (statusID.equals("1")) {
                        list.add(new BookDTO(productID, productName, description, categoryName, productPrice, productQuantity, image));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }
    
}
