package com.hj.clinic.clinics;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of = "id")
@Entity
public class Clinic {
	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private ClinicCollection clinicCollection = ClinicCollection.N;

	private String city;

	private String town;

	private String clinicName;

	private String address;

	private String calling;

	@Override
	public String toString() {
		return "Clinic [id=" + id + ", clinicCollection=" + clinicCollection + ", city=" + city + ", town=" + town
				+ ", clinicName=" + clinicName + ", address=" + address + ", calling=" + calling + "]";
	}
}
