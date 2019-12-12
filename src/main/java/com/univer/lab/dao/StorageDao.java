package com.univer.lab.dao;

import com.univer.lab.model.Provider;
import com.univer.lab.model.Sales;
import com.univer.lab.model.Storage;
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

public class StorageDao extends DBConnection implements BaseDao<Storage>{

    public static final String FIND_ALL_QUERY = "SELECT * FROM storage";
    public static final String UPDATE_ALL_QUERY = "UPDATE storage SET storage_number WHERE storage_id = ?";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM storage WHERE storage_id = ?";
    public static final String INSERT_ALL_QUERY = "INSERT INTO storage (storage_number) VALUES (?)";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM storage WHERE storage_id = ?";
    public static final String GET_LIST_OF_PROVIDERS = "SELECT * FROM provider WHERE storage_id=? ";


    public static final Logger LOGGER = getLogger(getClassName());

    @Override
    public Storage findById(Long id){
        Storage storage = null;
        LOGGER.trace("Started finding by id {} in database", id);
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);
            statement.setLong(1,id);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                storage = new Storage();
                storage.setStorageId(id);
                storage.setStorageNumber(set.getLong("storage_number"));
                storage.setProviders(getListProvider(storage));
            }
            LOGGER.trace("Storage {} found by id successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Storage {} wasn't found in database", id, e);
        }
        return storage;
    }

    @Override
    public List<Storage> findAll(){
        List<Storage> resultList = new ArrayList<>();
        LOGGER.trace("Started finding all in database");
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(FIND_ALL_QUERY);

            while (set.next()){
                Storage storage = new Storage();
                storage.setStorageNumber(set.getLong("storage_number"));
                storage.setProviders(getListProvider(storage));

                resultList.add(storage);
            }
            LOGGER.trace("Storage found all successfully");
        } catch (SQLException e){
            LOGGER.warn("Storage wasn't found in database",e);
        }
        return resultList;
    }

    @Override
    public Long save(Storage storage) {
        try {
            String actionQuery = (storage.getStorageId() == null) ? INSERT_ALL_QUERY
                    : UPDATE_ALL_QUERY;
            PreparedStatement statement = connection.prepareStatement(actionQuery);

            statement.setLong(1,storage.getStorageNumber());

            if (storage.getStorageId() != null) {
                statement.setLong(2, storage.getStorageId());
            }

            statement.execute();
            LOGGER.trace("Storage {} entered all in database", storage);
        }catch (SQLException e){
            LOGGER.warn("Storage {} wasn't entered in database", storage);
        }
        return storage.getStorageId();
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement statement;
        LOGGER.trace("Started deleting storage with id {} from database", id);
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1,id);
            statement.execute();
            LOGGER.trace("Storage with id {} deleted successfully", id);
        }catch (SQLException e){
            LOGGER.warn("Storage {} wasn't delete in database", id, e);
        }
    }

    public List<Provider> getListProvider(Storage storage){
        List<Provider> providers = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_LIST_OF_PROVIDERS);

            statement.setLong(1,storage.getStorageId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Provider provider = new ProviderDao().resultSetToObj(resultSet);
                providers.add(provider);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return providers;
    }
}
