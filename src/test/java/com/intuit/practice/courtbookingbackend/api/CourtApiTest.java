package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.model.Court;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourtApiTest {
    @InjectMocks
    private CourtApi courtApi = spy(new CourtApi());

    @Mock
    private ResultSet resultSet;

    @Test
    public void validateGetSlotByCourtId() throws SQLException {
        doReturn(resultSet).when(courtApi).getEntriesOfCourtTable();
        doReturn(true).doReturn(false).when(resultSet).next();
        doReturn("Bangalore").when(resultSet).getString("city");
        doReturn("Badminton").when(resultSet).getString("game");
        doReturn("Playzoo").when(resultSet).getString("name");
        doReturn(400).when(resultSet).getInt("min_cost");
        doReturn(600).when(resultSet).getInt("max_cost");

        HashMap<String, HashMap<String, List<Court>>> result = courtApi.getCourtsByCity();

        assertThat(result.size() == 1);
        assertThat(result.get("Bangalore").size() == 1);
        assertThat(result.get("Bangalore").get("Badminton").get(0).getCourtName().equals("Playzoo"));
        assertThat(result.get("Bangalore").get("Badminton").get(0).getMin().equals(400));
        assertThat(result.get("Bangalore").get("Badminton").get(0).getMax().equals(600));
    }
}