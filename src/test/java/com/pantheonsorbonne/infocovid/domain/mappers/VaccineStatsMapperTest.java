package com.pantheonsorbonne.infocovid.domain.mappers;

import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VaccineStatsMapperTest {

    private final String csv = "headers\n0;2020-03-17;200;300;400";

    @Test
    void should_return_object_from_csv() {

        List<VaccineStats> result = VaccineStatsMapper.csvToList(csv);

        assertEquals(1, result.size());
        assertEquals(LocalDate.of(2020, 03, 17), result.get(0).getDate());
        assertEquals(200, result.get(0).getN_dose1());
        assertEquals(300, result.get(0).getN_dose2());
        assertEquals(400, result.get(0).getN_cum_dose1());

    }

}