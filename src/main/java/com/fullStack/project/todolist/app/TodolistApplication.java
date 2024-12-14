package com.fullStack.project.todolist.app;

import com.fullStack.project.todolist.service.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistApplication implements CommandLineRunner {

	@Autowired
	ICommentService commentService;

	/*@Autowired
	ICustomDateService customDateService;

	@Autowired
	IDescriptionService descriptionService;

	@Autowired
	IEstimationService estimationService;

	@Autowired
	IExampleService exampleService;

	@Autowired
	IExampleFileService exampleFileService;

	@Autowired
	IUrgencyLevelService urgencyLevelService;

	@Autowired
	IStatusService statusService;*/

	@Autowired
	ITaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        try {
            commentService.findAll().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new RuntimeException(e);
        }
    }
		// Création des DTOs
		/*TaskDto taskDto = new TaskDto();
		CustomDateDto customDateDtoTask = new CustomDateDto();
		CustomDateDto customDateDtoComment = new CustomDateDto();
		CustomDateDto customDateDtoDescription = new CustomDateDto();
		CustomDateDto customDateDtoEstimation = new CustomDateDto();
		CustomDateDto customDateDtoExample = new CustomDateDto();
		CustomDateDto customDateDtoExampleFile = new CustomDateDto();
		CustomDateDto customDateDtoStatus = new CustomDateDto();
		CustomDateDto customDateDtoUrgencyLevel = new CustomDateDto();
		CommentDto commentDto = new CommentDto();
		DescriptionDto descriptionDto = new DescriptionDto();
		EstimationDto estimationDto = new EstimationDto();
		ExampleDto exampleDto = new ExampleDto();
		ExampleFileDto exampleFileDto = new ExampleFileDto();
		StatusDto statusDto = new StatusDto();
		UrgencyLevelDto urgencyLevelDto = new UrgencyLevelDto();

		// Initialisation des DTOs et autres objets
		taskDto.setObjective("objective task test7");

		// Initialisation des CustomDateDto pour chaque service
		// customDateTask
		customDateDtoTask.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoTask.setTaskId(6L);
		customDateDtoTask.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

		// customDateStatus
		customDateDtoStatus.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoStatus.setStatusId(6L);
		customDateDtoStatus.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

		// customDateComment
		customDateDtoComment.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoComment.setCommentId(6L);
		customDateDtoComment.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

		// customDateDescription
		customDateDtoDescription.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoDescription.setDescriptionId(6L);
		customDateDtoDescription.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

		// customDateEstimation
		customDateDtoEstimation.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoEstimation.setEstimationId(6L);
		customDateDtoEstimation.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

		// customDateExample
		customDateDtoExample.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoExample.setExampleId(6L);
		customDateDtoExample.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

		// customDateExampleFile
		customDateDtoExampleFile.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoExampleFile.setExampleFileId(6L);
		customDateDtoExampleFile.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

		// customDateUrgencyLevel
		customDateDtoUrgencyLevel.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoUrgencyLevel.setUrgencyLevelId(6L);
		customDateDtoUrgencyLevel.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));


		// Initialisation des DTOs de base
		commentDto.setCommentText("random comment for task 6");
		commentDto.setTaskId(6L);
		descriptionDto.setDescriptionText("random description text for task 6");
		descriptionDto.setTaskId(6L);
		estimationDto.setEstimationTime(3600);
		estimationDto.setTaskId(6L);
		exampleDto.setExampleText("example Text for task 6");
		exampleDto.setTaskId(6L);
		exampleFileDto.setFile(new Byte[] {1, 2, 3, 4, 5});
		exampleFileDto.setExampleId(6L);
		statusDto.setStatus(StatusEnum.IN_PROGRESS);
		statusDto.setTaskId(6L);
		urgencyLevelDto.setUrgencyLevel(UrgencyEnum.NORMAL);
		urgencyLevelDto.setTaskId(6L);

		// Création des objets via les services avec gestion des erreurs
		createEntity(taskService, taskDto);
		createEntity(commentService, commentDto);
		createEntity(descriptionService, descriptionDto);
		createEntity(estimationService, estimationDto);
		createEntity(exampleService, exampleDto);
		createEntity(exampleFileService, exampleFileDto);
		createEntity(statusService, statusDto);
		createEntity(urgencyLevelService, urgencyLevelDto);
		createEntity(customDateService, customDateDtoComment);
		createEntity(customDateService, customDateDtoDescription);
		createEntity(customDateService, customDateDtoEstimation);
		createEntity(customDateService, customDateDtoExample);
		createEntity(customDateService, customDateDtoExampleFile);
		createEntity(customDateService, customDateDtoStatus);
		createEntity(customDateService, customDateDtoTask);
		createEntity(customDateService, customDateDtoUrgencyLevel);

		// Exemple de mise à jour, suppression et récupération

		taskDto.setId(4L);
		customDateDtoTask.setId(16L);
		customDateDtoComment.setId(8L);
		customDateDtoDescription.setId(9L);
		customDateDtoEstimation.setId(10L);
		customDateDtoExample.setId(11L);
		customDateDtoExampleFile.setId(12L);
		customDateDtoStatus.setId(13L);
		customDateDtoUrgencyLevel.setId(14L);
		commentDto.setId(4L);
		descriptionDto.setId(4L);
		estimationDto.setId(4L);
		exampleDto.setId(4L);
		exampleFileDto.setId(4L);
		statusDto.setId(4L);
		urgencyLevelDto.setId(4L);

		customDateDtoTask.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoTask.setTaskId(5L);
		customDateDtoTask.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

// customDateStatus
		customDateDtoStatus.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoStatus.setStatusId(5L);
		customDateDtoStatus.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

// customDateComment
		customDateDtoComment.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoComment.setCommentId(5L);
		customDateDtoComment.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

// customDateDescription
		customDateDtoDescription.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoDescription.setDescriptionId(5L);
		customDateDtoDescription.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

// customDateEstimation
		customDateDtoEstimation.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoEstimation.setEstimationId(5L);
		customDateDtoEstimation.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

// customDateExample
		customDateDtoExample.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoExample.setExampleId(5L);
		customDateDtoExample.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

// customDateExampleFile
		customDateDtoExampleFile.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoExampleFile.setExampleFileId(5L);
		customDateDtoExampleFile.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));

// customDateUrgencyLevel
		customDateDtoUrgencyLevel.setTypeDate(TypeDateEnum.CLOSING_DATE);
		customDateDtoUrgencyLevel.setUrgencyLevelId(5L);
		customDateDtoUrgencyLevel.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));


		updateEntity(taskService, taskDto.getId(), taskDto);
		updateEntity(commentService, commentDto.getId(), commentDto);
		updateEntity(descriptionService, descriptionDto.getId(), descriptionDto);
		updateEntity(estimationService, estimationDto.getId(), estimationDto);
		updateEntity(exampleService, exampleDto.getId(), exampleDto);
		updateEntity(exampleFileService, exampleFileDto.getId(), exampleFileDto);
		updateEntity(statusService, statusDto.getId(), statusDto);
		updateEntity(urgencyLevelService, urgencyLevelDto.getId(), urgencyLevelDto);
		updateEntity(customDateService, customDateDtoComment.getId(), customDateDtoComment);
		updateEntity(customDateService, customDateDtoDescription.getId(), customDateDtoDescription);
		updateEntity(customDateService, customDateDtoEstimation.getId(), customDateDtoEstimation);
		updateEntity(customDateService, customDateDtoExample.getId(), customDateDtoExample);
		updateEntity(customDateService, customDateDtoExampleFile.getId(), customDateDtoExampleFile);
		updateEntity(customDateService, customDateDtoStatus.getId(), customDateDtoStatus);
		updateEntity(customDateService, customDateDtoTask.getId(), customDateDtoTask);
		updateEntity(customDateService, customDateDtoUrgencyLevel.getId(), customDateDtoUrgencyLevel);

// Exemple de suppression

		taskDto.setId(4L);
		customDateDtoTask.setId(26L);
		customDateDtoComment.setId(27L);
		customDateDtoDescription.setId(28L);
		customDateDtoEstimation.setId(29L);
		customDateDtoExample.setId(30L);
		customDateDtoExampleFile.setId(31L);
		customDateDtoStatus.setId(32L);
		customDateDtoUrgencyLevel.setId(33L);
		commentDto.setId(5L);
		descriptionDto.setId(5L);
		estimationDto.setId(5L);
		exampleDto.setId(5L);
		exampleFileDto.setId(5L);
		statusDto.setId(5L);
		urgencyLevelDto.setId(5L);


		deleteEntity(taskService, taskDto.getId());
		deleteEntity(commentService, commentDto.getId());
		deleteEntity(descriptionService, descriptionDto.getId());
		deleteEntity(estimationService, estimationDto.getId());
		deleteEntity(exampleService, exampleDto.getId());
		deleteEntity(exampleFileService, exampleFileDto.getId());
		deleteEntity(statusService, statusDto.getId());
		deleteEntity(urgencyLevelService, urgencyLevelDto.getId());
		deleteEntity(customDateService, customDateDtoComment.getId());
		deleteEntity(customDateService, customDateDtoDescription.getId());
		deleteEntity(customDateService, customDateDtoEstimation.getId());
		deleteEntity(customDateService, customDateDtoExample.getId());
		deleteEntity(customDateService, customDateDtoExampleFile.getId());
		deleteEntity(customDateService, customDateDtoStatus.getId());
		deleteEntity(customDateService, customDateDtoTask.getId());
		deleteEntity(customDateService, customDateDtoUrgencyLevel.getId());


		// Exemple de récupération
		// Exemple de récupération de toutes les entités
		getAllEntity(taskService);
		getAllEntity(commentService);
		getAllEntity(descriptionService);
		getAllEntity(estimationService);
		getAllEntity(exampleService);
		getAllEntity(exampleFileService);
		getAllEntity(statusService);
		getAllEntity(urgencyLevelService);
		getAllEntity(customDateService);

// Exemple de récupération d'une entité par ID
		getEntityById(taskService, taskDto.getId());
		getEntityById(commentService, commentDto.getId());
		getEntityById(descriptionService, descriptionDto.getId());
		getEntityById(estimationService, estimationDto.getId());
		getEntityById(exampleService, exampleDto.getId());
		getEntityById(exampleFileService, exampleFileDto.getId());
		getEntityById(statusService, statusDto.getId());
		getEntityById(urgencyLevelService, urgencyLevelDto.getId());
		getEntityById(customDateService, customDateDtoComment.getId());
		getEntityById(customDateService, customDateDtoDescription.getId());
		getEntityById(customDateService, customDateDtoEstimation.getId());
		getEntityById(customDateService, customDateDtoExample.getId());
		getEntityById(customDateService, customDateDtoExampleFile.getId());
		getEntityById(customDateService, customDateDtoStatus.getId());
		getEntityById(customDateService, customDateDtoTask.getId());
		getEntityById(customDateService, customDateDtoUrgencyLevel.getId());

	}

	// Méthode pour initialiser un CustomDateDto
	private void initCustomDateDto(CustomDateDto customDateDto, Long id, TypeDateEnum typeDate) {
		customDateDto.setTypeDate(typeDate);
		customDateDto.setTaskId(id); // Vous pouvez changer cette ligne en fonction du type d'ID (par exemple commentId, estimationId, etc.)
		customDateDto.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));
	}

	// Méthode générique pour créer une entité via un service
	private <T> void createEntity(Object service, T dto) {
		try {
			Method method = service.getClass().getMethod("create", dto.getClass());
			method.invoke(service, dto);
		} catch (Exception e) {
			logError(e);
		}
	}

	// Méthode générique pour mettre à jour une entité via un service
	private <T> void updateEntity(Object service, Long id, T dto) {
		try {
			// Récupération de la méthode "update" avec deux paramètres (Long, T)
			Method method = service.getClass().getMethod("update", Long.class, dto.getClass());

			// Invocation de la méthode "update" avec les arguments id et dto
			method.invoke(service, id, dto);
		} catch (Exception e) {
			logError(e);
		}
	}

	private void deleteEntity(Object service, Long id) {
		try {
			// Récupérer la méthode "delete" qui prend un Long en paramètre
			Method method = service.getClass().getMethod("delete", Long.class);

			// Invoquer la méthode "delete" avec l'identifiant
			method.invoke(service, id);
		} catch (Exception e) {
			logError(e);
		}
	}
	private <T> void getEntityById(Object service, Long id) {
		try {
			Method method = service.getClass().getMethod("getAll", Long.class);
			method.invoke(service, id);
		} catch (Exception e) {
			logError(e);
		}
	}

	// Méthode générique pour récupérer une entité via un service
	private void getAllEntity(Object service) {
		try {
			Method method = service.getClass().getMethod("getAll");
			method.invoke(service);
		} catch (Exception e) {
			logError(e);
		}
	}

	// Méthode pour loguer les erreurs
	private void logError(Exception e) {
		System.out.println("Error");
		System.out.println(e.getMessage());
		System.out.println(e.getClass());
		System.out.println(e.getCause());
	}*/

}

