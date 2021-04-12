package com.pantheonsorbonne.infocovid.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Sert à stocker les adresses mail des abonnés, pour l'envoi du rapport journalier
 */
@Document(collection = "emails")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class EmailAddressDTO {

	/**
	 * Adresse mail
	 */
    @Id
    @JsonProperty("address")
    String address;

}
