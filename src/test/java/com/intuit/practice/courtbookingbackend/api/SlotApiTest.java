package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.model.SlotModal;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SlotApiTest {
    @InjectMocks
    private SlotApi slotApi = spy(new SlotApi());

    @Mock
    private ResultSet resultSet;

    @Mock
    private Timestamp startTime;

    @Mock
    private Timestamp endTime;

    @Test
    public void validateGetSlotByCourtId() throws SQLException {
        doReturn(resultSet).when(slotApi).getAllAvailableSlots(anyInt());
        doReturn(false).when(resultSet).next();

        HashMap<String, List<SlotModal>> result = slotApi.getSlotByCourtId(1);

        assertThat(result.size() == 0);
    }

    @Test
    public void validateGetSlotsWithData() throws SQLException {
        doReturn(resultSet).when(slotApi).getAllAvailableSlots(anyInt());
        doReturn(startTime).when(resultSet).getTimestamp("start_time");
        doReturn(endTime).when(resultSet).getTimestamp("end_time");
        doReturn("Available").when(resultSet).getString("status");
        doReturn(true).doReturn(false).when(resultSet).next();

        HashMap<String, List<SlotModal>> result = slotApi.getSlotByCourtId(1);

        assertThat(result.size() == 1);
    }
}