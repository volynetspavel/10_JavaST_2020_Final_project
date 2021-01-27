package com.volynets.edem.service;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.volynets.edem.entity.Animal;
import com.volynets.edem.exception.ServiceException;
import com.volynets.edem.service.factory.ServiceFactory;

public class TestAnimalService {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private AnimalService animalService = serviceFactory.getAnimalService();

	private Animal animalForUpdate = new Animal();

	@BeforeClass
	public void setUpAnimalForUpdate() {
		int id = 5;
		String name = "Wolverine";
		String desc = "Hello! Polar bears live";
		String content = "Climate";
		String logo = "/img/an";
		int co2 = 1050;

		animalForUpdate.setId(id);
		animalForUpdate.setName(name);
		animalForUpdate.setDesc(desc);
		animalForUpdate.setContent(content);
		animalForUpdate.setLogo(logo);
		animalForUpdate.setCo2(co2);
	}

	@Test
	public void testFindAll() throws ServiceException {
		List<Animal> animals = animalService.findAll();

		// print Animals to console
		StringBuilder stringBuilder = new StringBuilder();
		for (Animal animal : animals) {
			stringBuilder.append(animal.toString()).append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

	@Test
	public void testDeleteAnimal() throws ServiceException {
		int idAnimal = 4;
		animalService.delete(idAnimal);

		Animal animal = null;
		animal = animalService.findById(idAnimal);

		Assert.assertNull(animal);
	}

	@Test(description = "Check update of object from database.")
	public void testUpdate() throws ServiceException {
		animalService.update(animalForUpdate);
	}
}
