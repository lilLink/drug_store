package com.univer.lab.dao;

import com.univer.lab.model.Provider;
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

public class ProviderDao extends DBConnection implements BaseDao<Provider> {

    public static final String FIND_ALL_QUERY = "SELECT * FROM provider";
    public static final String UPDATE_ALL_QUERY = "UPDATE provider SET provider_name = ? WHERE provider_id = ?";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM provider WHERE provider_id = ?";
    public static final String INSERT_ALL_QUERY = "INSERT INTO provider (provider_name) VALUES (?)";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM provider WHERE provider_id = ?";

    public static final Logger LOGGER = getLogger(getClassName());

    @Override
    public Provider findById(Long id){
        Provider provider = null;
        LOGGER.trace("Started finding by id {} in database", id);
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);
            statement.setLong(1,id);
            ResultSet set = statement.executeQuery();
            if (set.first()){
                provider = new Provider();
                provider.setProviderId(id);
                provider.setProviderName(set.getString("provider_name"));
            }
            LOGGER.trace("Provider {} found by id successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Provider {} wasn't found in database", id, e);
        }
        return provider;
    }

    @Override
    public List<Provider> findAll(){
        List<Provider> resultList = new ArrayList<>();
        LOGGER.trace("Started finding all in database");
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(FIND_ALL_QUERY);

            while (set.next()){
                Provider provider = new Provider();
                provider.setProviderName(set.getString("provider_name"));

                resultList.add(provider);
            }
            LOGGER.trace("Provider found all successfully");
        } catch (SQLException e){
            LOGGER.warn("Provider wasn't found in database",e);
        }
        return resultList;
    }

    @Override
    public Long save(Provider provider) {
        try {
            String actionQuery = (provider.getProviderId() == null) ? INSERT_ALL_QUERY
                    : UPDATE_ALL_QUERY;
            PreparedStatement statement = connection.prepareStatement(actionQuery);


            statement.setString(1,provider.getProviderName());

            if (provider.getProviderId() != null) {
                statement.setLong(2, provider.getProviderId());
            }

            statement.execute();
            LOGGER.trace("Provider {} entered all in database", provider);
        }catch (SQLException e){
            LOGGER.warn("Provider {} wasn't entered in database", provider);
        }
        return provider.getProviderId();
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement statement;
        LOGGER.trace("Started deleting provider with id {} from database", id);
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1,id);
            statement.execute();
            LOGGER.trace("Provider with id {} deleted successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Provider {} wasn't delete in database", id, e);
        }
    }
}
