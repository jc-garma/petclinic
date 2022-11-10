/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.owner.dao.OwnerRepository;
import org.springframework.samples.petclinic.owner.dto.Visit;
import org.springframework.samples.petclinic.vet.dto.Specialty;
import org.springframework.samples.petclinic.vet.dao.SpecialtyRepository;
import org.springframework.samples.petclinic.vet.dto.Vet;
import org.springframework.samples.petclinic.vet.dao.VetRepository;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.samples.petclinic.bill.dao.BillRepository;

import java.time.LocalDate;
import java.util.Date;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 */

@Slf4j
@SpringBootApplication
public class PetClinicApplication {

	public static void main(String[] args) {
		log.info("prueba");
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialtyRepository specialtyRepository, OwnerRepository ownerRepository, BillRepository billRepository) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");

			log.info("Creamos un objeto Vet sin especialidad");
			Vet vet = new Vet();
			vet.setFirstName("Sergio");
			vet.setLastName("Raposo Vargas");

			log.info("Persistimos en BBDD");
			vetRepository.save(vet);

			log.info("Comprobamos que se ha creado correctamente");
			Vet vetAux = vetRepository.findById(vet.getId());
			log.info(vetAux.toString());

			log.info("Editamos el objeto y añadimos una Speciality");
			Specialty s = specialtyRepository.findById(1);
			vet.addSpecialty(s);
			vetRepository.save(vet);
			log.info(vet.toString());

			log.info("Listamos todos los veterinarios");
			for (Vet v : vetRepository.findAll()) {
				log.info("Vet: " + v);
			}

			log.info("Listamos todos los veterinarios para la especialidad “radiology”");
			for (Vet v : vetRepository.findBySpecialityName("radiology")) {
				log.info("Vet: " + v);
			}

			log.info("Creamos un objeto Bill sin fecha");
			Bill bill = new Bill();
			bill.setMoney(20.55);
			bill.setPaymentDate(new Date());

			billRepository.save(bill);

			/*log.info("Creamos una objeto Visit con factura");
			Visit visit = new Visit();
			LocalDate date = LocalDate.parse("2022-11-08");
			visit.setDate(date);
			visit.setBill(bill);

			bill.setVisit(visit);
			billRepository.save(bill);
			ownerRepository.save(visit);*/


		};
	}

}
