package com.univer.lab.dao;

import com.univer.lab.model.Sales;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.univer.lab.utility.ClassNameUtil.getClassName;
import static org.apache.logging.log4j.LogManager.getLogger;

public class SalesDao extends DBConnection implements BaseDao<Sales> {

    public static final String FIND_ALL_QUERY = "SELECT * FROM sales";
    public static final String UPDATE_ALL_QUERY = "UPDATE sales SET realization_date = ?, realization = ?, drug_id = ? WHERE sale_id = ?";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM sales WHERE sale_id = ?";
    public static final String INSERT_ALL_QUERY = "INSERT INTO sales (realization_date,realization,drug_id) VALUES (?,?,?)";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM sales WHERE sale_id = ?";

    public static final Logger LOGGER = getLogger(getClassName());

    @Override
    public Sales findById(Long id){
        Sales sale = null;
        LOGGER.trace("Started finding by id {} in database", id);
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);
            statement.setLong(1,id);
            ResultSet set = statement.executeQuery();
            if (set.first()){
                sale = new Sales();
                sale.setSaleId(id);
                sale.setRealization(set.getLong("realization"));
                sale.setRealizationDate(LocalDate.parse(set.getDate("realization_date").toString()));
            }
            LOGGER.trace("Sales {} found by id successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Sales {} wasn't found in database", id, e);
        }
        return sale;
    }

    @Override
    public List<Sales> findAll(){
        List<Sales> resultList = new ArrayList<>();
        LOGGER.trace("Started finding all in database");
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(FIND_ALL_QUERY);

            while (set.next()){
                Sales sale = new Sales();
                sale.setRealization(set.getLong("realization"));
                sale.setRealizationDate(LocalDate.parse(set.getDate("realization_date").toString()));

                resultList.add(sale);
            }
            LOGGER.trace("Sales found all successfully");
        } catch (SQLException e){
            LOGGER.warn("Sales wasn't found in database",e);
        }
        return resultList;
    }

    @Override
    public Long save(Sales sale) {
        try {
            String actionQuery = (sale.getSaleId() == null) ? INSERT_ALL_QUERY
                    : UPDATE_ALL_QUERY;
            PreparedStatement statement = connection.prepareStatement(actionQuery);


            statement.setLong(1,sale.getRealization());
            statement.setTimestamp(2, Timestamp.valueOf(sale.getRealizationDate().atStartOfDay()));
            statement.setLong(3,sale.getDrug().getDrugId());

            if (sale.getSaleId() != null) {
                statement.setLong(3, sale.getSaleId());
            }

            statement.execute();
            LOGGER.trace("Sales {} entered all in database", sale);
        }catch (SQLException e){
            LOGGER.warn("Sales {} wasn't entered in database", sale);
        }
        return sale.getSaleId();
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement statement;
        LOGGER.trace("Started deleting sale with id {} from database", id);
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1,id);
            statement.execute();
            LOGGER.trace("Sales with id {} deleted successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Sales {} wasn't delete in database", id, e);
        }
    }
}
