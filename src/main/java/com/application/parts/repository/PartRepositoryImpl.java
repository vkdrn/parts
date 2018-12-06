package com.application.parts.repository;

import com.application.parts.db.Database;
import com.application.parts.model.Part;
import com.application.parts.utils.PartUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartRepositoryImpl implements PartRepository {

    @Override
    public List<Part> filterParts(Map<String, String> stringParamMap) {

        Connection connection = null;

        List<Part> parts = null;
        List<String> conditions = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM parts");

        if (stringParamMap != null && !stringParamMap.isEmpty()) {
            sql.append(" WHERE ");

            stringParamMap.forEach((k, v) -> {

                if (PartUtils.isDate(k)) {
                    if (k.toLowerCase().contains("after")) {
                        conditions.add(String.format("%s >= '%s'", k.replace("After", ""), v));
                    } else if (k.toLowerCase().contains("before")) {
                        conditions.add(String.format("%s <= '%s'", k.replace("Before", ""), v));
                    }
                } else if (PartUtils.isInteger(k) && PartUtils.validateInteger(v)) {
                    conditions.add(String.format("%s >= %s", k, v));
                } else {
                    conditions.add(k + " LIKE '%" + v + "%'");
                }

            });

            sql.append(String.join(" AND ", conditions));
        }

        PreparedStatement pstmt = null;

        try {
            connection = Database.getConnection();

            pstmt = connection.prepareStatement(sql.toString());

            ResultSet resultSet = pstmt.executeQuery();

            parts = fillResult(resultSet);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return parts;
    }

    private List<Part> fillResult(ResultSet resultSet) throws SQLException {
        List<Part> result = new ArrayList<>();

        while (resultSet.next()) {
            Part part = new Part();
            part.setId(resultSet.getLong(1));
            part.setPartName(resultSet.getString(2));
            part.setPartNumber(resultSet.getString(3));
            part.setVendor(resultSet.getString(4));
            part.setQty(resultSet.getInt(5));
            part.setShipped(resultSet.getDate(6));
            part.setReceive(resultSet.getDate(7));

            result.add(part);
        }

        return result;
    }
}
