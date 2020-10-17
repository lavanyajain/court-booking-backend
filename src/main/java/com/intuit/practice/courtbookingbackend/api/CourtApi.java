package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class CourtApi {
    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    private QueryExecutor queryExecutor = new QueryExecutor();

    private String sql;


    public HashMap<String,HashMap<String, List<Court>>> getCourtsByCity() throws SQLException {
        sql = "select * from court";
        String city, game;
        Court court;
        HashMap<String, List<Court>> cityList;
        List<Court> courtList = new ArrayList<>();
        QueryExecutorResponse response = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sql);
        ResultSet resultSet = response.getResultSet();
        HashMap<String, HashMap<String, List<Court>>> courts = new HashMap<>();
        while (resultSet.next()) {
            city = resultSet.getString("city");
            game = resultSet.getString("game");
            court = new Court(resultSet.getString("name"), resultSet.getInt("min_cost"), resultSet.getInt("max_cost"), resultSet.getString("game"));
            if(!courts.containsKey(city)) {
                HashMap<String, List<Court>> hashMap = new HashMap<>();
                courts.put(city, hashMap);
            }
            HashMap<String, List<Court>> hashMap = courts.get(city);
            if(!hashMap.containsKey(game)) {
              hashMap.put(game, new ArrayList<>());
            }
            List<Court> c = hashMap.get(game);
            c.add(court);
            hashMap.put(game, c);
            courts.put(city, hashMap);
            //for(String key: hashMap.keySet()) {
            //    List<Court> c = hashMap.get(key);
           // }
            //courts.put(city, )


            courtList.add(court);
            //courts.put(city, courtList);
        }

    return courts;
    }

    public HashMap<String, HashMap<String, List<Court>>> getAllCourts() throws SQLException {
        getCourtsByCity();
        sql = "select * from court";
        QueryExecutorResponse response = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sql);
        ResultSet resultSet = response.getResultSet();
        List<Court> courtList;
        Court court;
        String game;
        String city;
        HashMap<String, HashMap<String,List<Court>>> citySet = new HashMap<>();


        while (resultSet.next()) {
            city = resultSet.getString("city");
            sql = "select * from court where city='" + city + "';";
            QueryExecutorResponse response1 = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sql);
            ResultSet resultSet1 = response1.getResultSet();
            HashMap<String, List<Court>> gameSet = new HashMap<>();
            while (resultSet1.next()) {
                game = resultSet1.getString("game");
                sql = "select * from court where game='" + game + "' AND city='"+ city + "';";
                QueryExecutorResponse response2 = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sql);
                ResultSet resultSet2 = response2.getResultSet();
                courtList = new ArrayList<>();
                while (resultSet2.next()) {
                    court = new Court();
                    court.setMax(resultSet2.getInt("max_cost"));
                    court.setMin(resultSet2.getInt("min_cost"));
                    court.setCourtName(resultSet2.getString("name"));
                    courtList.add(court);
                }
                gameSet.put(game, courtList);
            }

            citySet.put(city, gameSet);

        }

        //return cityCourts1;
        return citySet;
    }
}
