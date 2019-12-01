package com.univer.lab.dao;

import com.univer.lab.model.Drug;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.univer.lab.utility.ClassNameUtil.getClassName;
import static org.apache.logging.log4j.LogManager.getLogger;

public class DrugDao extends DBConnection implements BaseDao<Drug> {

    public static final String FIND_ALL_QUERY = "SELECT * FROM drug";
    public static final String UPDATE_ALL_QUERY = "UPDATE drug SET country = ?, name = ?, price = ?, count = ? WHERE drug_id = ?";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM drug WHERE drug_id = ?";
    public static final String INSERT_ALL_QUERY = "INSERT INTO drug (country,name,price,count) VALUES (?,?,?,?)";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM drug WHERE drug_id = ?";

    public static final Logger LOGGER = getLogger(getClassName());

    @Override
    public Drug findById(Long id){
        Drug drug = null;
        LOGGER.trace("Started finding by id {} in database", id);
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);
            statement.setLong(1,id);
            ResultSet set = statement.executeQuery();
            if (set.first()){
                drug = new Drug();
                drug.setDrugId(id);
                drug.setCountry(set.getString("country"));
                drug.setName(set.getString("name"));
                drug.setPrice(set.getLong("price"));
                drug.setCount(set.getLong("count"));
            }
            LOGGER.trace("Drug {} found by id successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Drug {} wasn't found in database", id, e);
        }
        return drug;
    }

    @Override
    public List<Drug> findAll(){
        List<Drug> resultList = new ArrayList<>();
        LOGGER.trace("Started finding all in database");
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(FIND_ALL_QUERY);

            while (set.next()){
                Drug drug = new Drug();
                drug.setCountry(set.getString("country"));
                drug.setName(set.getString("name"));
                drug.setPrice(set.getLong("price"));
                drug.setCount(set.getLong("count"));

                resultList.add(drug);
            }
            LOGGER.trace("Drug found all successfully");
        } catch (SQLException e){
            LOGGER.warn("Drug wasn't found in database",e);
        }
        return resultList;
    }

    @Override
    public Long save(Drug drug) {
        try {
            String actionQuery = (drug.getDrugId() == null) ? INSERT_ALL_QUERY
                    : UPDATE_ALL_QUERY;
            PreparedStatement statement = connection.prepareStatement(actionQuery);


            statement.setString(1,drug.getCountry());
            statement.setString(2,drug.getName());
            statement.setLong(3,drug.getPrice());
            statement.setLong(4,drug.getCount());

            if (drug.getDrugId() != null) {
                statement.setLong(5, drug.getDrugId());
            }

            statement.execute();
            LOGGER.trace("Drug {} entered all in database", drug);
        }catch (SQLException e){
            LOGGER.warn("Drug {} wasn't entered in database", drug);
        }
        return drug.getDrugId();
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement statement;
        LOGGER.trace("Started deleting drug with id {} from database", id);
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1,id);
            statement.execute();
            LOGGER.trace("Drug with id {} deleted successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Drug {} wasn't delete in database", id, e);
        }
    }
}
