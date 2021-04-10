package com.pantheonsorbonne.infocovid.domain.mappers;

import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsRecapMapperTest {

    private final String csv = "headers\n2020-03-17,100,200,0.03,400.0,500,600,7,800,9,10,11,12,1300,1400,15,1600";

    @Test
    void should_return_object_from_csv() {

        List<StatsRecap> result = StatsRecapMapper.csvToList(csv);

        assertEquals(1, result.size());
        assertEquals(LocalDate.of(2020, 03, 17), result.get(0).getDate());
        assertEquals(800, result.get(0).getDchosp());
        assertEquals(1300, result.get(0).getConf());
        assertEquals(1400, result.get(0).getConf_j1());
        assertEquals(1600, result.get(0).getEsms_dc());
        assertEquals(100, result.get(0).getTx_pos());
        assertEquals(200, result.get(0).getTx_incid());
        assertEquals(400, result.get(0).getR());
        assertEquals(500, result.get(0).getRea());
        assertEquals(600, result.get(0).getHosp());
        assertEquals(3, result.get(0).getTo());

    }
}