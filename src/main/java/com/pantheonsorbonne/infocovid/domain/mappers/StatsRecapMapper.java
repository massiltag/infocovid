package com.pantheonsorbonne.infocovid.domain.mappers;

import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
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
 * Sert à convertir les lignes d'informations de vaccination du format CSV en liste d'objets {@link StatsRecap}
 */
@Mapper
public abstract class StatsRecapMapper {

    public static List<StatsRecap> csvToList(String csv) {
        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new StringReader(csv))) {
            return in.lines().skip(1).map(line -> {
                String[] x = pattern.split(csvInsertBlanks(line, ","));
                return StatsRecap.builder()
                        .date(stringToLocalDate(x[0]))
                        .dchosp(parseInt(x[8]))
                        .conf(parseInt(x[13]))
                        .conf_j1(parseInt(x[14]))
                        .esms_dc(parseInt(x[16]))
                        .tx_pos(parseDouble(x[1]))
                        .tx_incid(parseDouble(x[2]))
                        .r(parseDouble(x[4]))
                        .rea(parseInt(x[5]))
                        .hosp(parseInt(x[6]))
                        .to(parseDouble(x[3])*100)
                        .build();
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
