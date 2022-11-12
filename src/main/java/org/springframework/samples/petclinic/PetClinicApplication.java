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
import org.springframework.samples.petclinic.pet.dao.PetRepository;
import org.springframework.samples.petclinic.pet.dto.Pet;
import org.springframework.samples.petclinic.specialty.dto.Specialty;
import org.springframework.samples.petclinic.specialty.dao.SpecialtyRepository;
import org.springframework.samples.petclinic.vet.dto.Vet;
import org.springframework.samples.petclinic.vet.dao.VetRepository;
import org.springframework.samples.petclinic.visit.dao.VisitRepository;
import org.springframework.samples.petclinic.visit.dto.Visit;

import java.time.LocalDate;
import java.util.List;
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
		System.out.println("Inicio");
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialtyRepository specialtyRepository, PetRepository petRepository, VisitRepository visitRepository) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");

			log.info("Creamos un objeto Vet sin especialidad");
			Vet vet = new Vet();
			vet.setFirstName("Sergio");
			vet.setLastName("Raposo Vargas");
			log.info("*****************************************************");

			log.info("Persistimos en BBDD");
			vetRepository.save(vet);
			log.info("*****************************************************");

			log.info("Comprobamos que se ha creado correctamente");
			Vet vetAux = vetRepository.findById(vet.getId());
			log.info(vetAux.toString());
			log.info("*****************************************************");

			log.info("Editamos el objeto y añadimos una Speciality");
			Specialty s = specialtyRepository.findById(1);
			vet.addSpecialty(s);
			vetRepository.save(vet);
			log.info(vet.toString());
			log.info("*****************************************************");

			log.info("Listamos todos los veterinarios");
			for (Vet v : vetRepository.findAll()) {
				log.info("Vet: " + v);
			}
			log.info("*****************************************************");

			log.info("Listamos todos los veterinarios para la especialidad “radiology”");
			for (Vet v : vetRepository.findBySpecialityName("radiology")) {
				log.info("Vet: " + v);
			}
			log.info("*****************************************************");
			System.out.println("Mascotas nacidas en 2010, ordenadas ascendentemente por fecha de nacimiento");
			for (Pet p : petRepository.findByYearOfBirthDate(2010)) {
				System.out.println("Pet: " + p.getName() + " " + p.getBirthDate());
			}

			System.out.println("*****************************************************");

			System.out.println("Mascotas nacidas en 2010, ordenadas ascendentemente por fecha de nacimiento");
			for (Pet p : petRepository.findByYearOfBirthDate(2010)) {
				System.out.println("Pet: " + p.getName() + " \t " + p.getBirthDate());
			}

			System.out.println("*****************************************************");

			System.out.println("Crear 3 visitas para diferentes mascotas");
			for (int i = 0; i < 3; i++) {
				Pet p = petRepository.findById(i + 1);
				Visit v = new Visit();
				if (i % 3 == 0) {
					v.setDate(LocalDate.of(2010, 1, 15));
				}
				if (i % 3 == 1) {
					v.setDate(LocalDate.of(2021, 3, 15));
				}
				if (i % 3 == 2) {
					v.setDate(LocalDate.of(2022, 5, 15));
				}
				v.setDescription("Routine check");
				visitRepository.save(v);
				p.addVisit(v);
				petRepository.save(p);
				System.out.println(p.toString());
			}

			System.out.println("*****************************************************");

			System.out.println("Obtener todas las visitas para una mascota");
			Pet pet = petRepository.findById(2);
			for (Visit v : pet.getVisits()) {
				System.out.println("Visit: " + v.getId() + " " + v.getDate() + " - " + v.getDescription());
			}
			System.out.println("*****************************************************");

			System.out.println("Obtener las 4 visitas más recientes de todo el sistema");
			List<Visit> v = visitRepository.findAllInDescendingOrder();
			for (int i = 0; i < 4; i++) {
				System.out.println("Visit: " + v.get(i).getDate());
			}
			System.out.println("*****************************************************");
		};
	}

}
