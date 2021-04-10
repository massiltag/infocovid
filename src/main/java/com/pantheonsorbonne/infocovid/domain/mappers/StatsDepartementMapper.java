package com.pantheonsorbonne.infocovid.domain.mappers;

import com.pantheonsorbonne.infocovid.domain.dto.StatsDepartementDTO;
import org.mapstruct.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.pantheonsorbonne.infocovid.util.StringUtil.*;

/**
 * Sert à convertir les lignes d'informations de vaccination du format CSV en liste d'objets {@link StatsDepartementDTO}
 */
@Mapper
public abstract class StatsDepartementMapper {

    public static List<StatsDepartementDTO> csvToList(String csv) {
        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new StringReader(csv))) {
            return in.lines()
                    .skip(1)
                    .filter(line -> pattern.split(csvInsertBlanks(line, ","))[1]
                            .equals(localDateToString(LocalDate.now().minusDays(4))))
                    .map(line -> {
                        String[] x = pattern.split(csvInsertBlanks(line, ","));
                        return StatsDepartementDTO.builder()
                                .dep(x[0])
                                .date(stringToLocalDate(x[1]))
                                .pos(parseInt(x[19]))
                                .build();
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
