package com.pantheonsorbonne.infocovid.domain.mappers;

import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;
import org.mapstruct.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.pantheonsorbonne.infocovid.util.StringUtil.*;

/**
 * Sert à convertir les lignes d'informations de vaccination du format CSV en liste d'objets {@link VaccineStats}
 */
@Mapper
public abstract class VaccineStatsMapper {

    public static List<VaccineStats> csvToList(String csv) {
        Pattern pattern = Pattern.compile(";");
        try (BufferedReader in = new BufferedReader(new StringReader(csv))) {
            return in.lines().skip(1).map(line -> {
                String[] x = pattern.split(csvInsertBlanks(line, ";"));
                return VaccineStats.builder()
                        .date(stringToLocalDate(x[1]))
                        .n_dose1(parseInt(x[2]))
                        .n_dose2(parseInt(x[3]))
                        .n_cum_dose1(parseInt(x[4]))
                        .build();
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
