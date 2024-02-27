/*
 * package com.ccsw.tutorial.prestamo;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull;
 * 
 * import java.util.List;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.web.client.TestRestTemplate; import
 * org.springframework.boot.test.web.server.LocalServerPort; import
 * org.springframework.core.ParameterizedTypeReference; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.test.annotation.DirtiesContext;
 * 
 * import com.ccsw.tutorial.common.pagination.PageableRequest; import
 * com.ccsw.tutorial.config.ResponsePage; import
 * com.ccsw.tutorial.prestamo.model.PrestamoDto; import
 * com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
 * 
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * 
 * @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 * public class PrestamoIT {
 * 
 * public static final String LOCALHOST = "http://localhost:"; public static
 * final String SERVICE_PATH = "/prestamo";
 * 
 * public static final Long DELETE_PRESTAMO_ID = 6L; public static final Long
 * MODIFY_PRESTAMO_ID = 3L; public static final String NEW_PRESTAMO_NAME =
 * "Nuevo Autor"; public static final String NEW_CLIENT_NAME = "NuevoNombre";
 * public static final GameDto NEW_PRESTAMO_TITLE = "Nuevo Autor"; public static
 * final String NEW_CLIENT_NAME = "NuevoNombre";
 * 
 * private static final int TOTAL_PRESTAMOS = 6; private static final int
 * PAGE_SIZE = 5;
 * 
 * @LocalServerPort private int port;
 * 
 * @Autowired private TestRestTemplate restTemplate;
 * 
 * ParameterizedTypeReference<ResponsePage<PrestamoDto>> responseTypePage = new
 * ParameterizedTypeReference<ResponsePage<PrestamoDto>>() { };
 * 
 * @Test public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {
 * 
 * PrestamoSearchDto searchDto = new PrestamoSearchDto();
 * searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
 * 
 * ResponseEntity<ResponsePage<PrestamoDto>> response =
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new
 * HttpEntity<>(searchDto), responseTypePage);
 * 
 * assertNotNull(response); assertEquals(TOTAL_PRESTAMOS,
 * response.getBody().getTotalElements()); assertEquals(PAGE_SIZE,
 * response.getBody().getContent().size()); }
 * 
 * @Test public void findSecondPageWithFiveSizeShouldReturnLastResult() {
 * 
 * int elementsCount = TOTAL_PRESTAMOS - PAGE_SIZE;
 * 
 * PrestamoSearchDto searchDto = new PrestamoSearchDto();
 * searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));
 * 
 * ResponseEntity<ResponsePage<PrestamoDto>> response =
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new
 * HttpEntity<>(searchDto), responseTypePage);
 * 
 * assertNotNull(response); assertEquals(TOTAL_PRESTAMOS,
 * response.getBody().getTotalElements()); assertEquals(elementsCount,
 * response.getBody().getContent().size()); }
 * 
 * @Test public void saveWithoutIdShouldCreateNewPrestamo() {
 * 
 * long newPrestamoId = TOTAL_PRESTAMOS + 1; long newPrestamoSize =
 * TOTAL_PRESTAMOS + 1;
 * 
 * PrestamoDto dto = new PrestamoDto(); dto.setTitle(NEW_PRESTAMO_TITLE);
 * dto.setName(NEW_CLIENT_NAME);
 * 
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new
 * HttpEntity<>(dto), Void.class);
 * 
 * PrestamoSearchDto searchDto = new PrestamoSearchDto();
 * searchDto.setPageable(new PageableRequest(0, (int) newPrestamoSize));
 * 
 * ResponseEntity<ResponsePage<PrestamoDto>> response =
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new
 * HttpEntity<>(searchDto), responseTypePage);
 * 
 * assertNotNull(response); assertEquals(newPrestamoSize,
 * response.getBody().getTotalElements());
 * 
 * PrestamoDto prestamo = response.getBody().getContent().stream() .filter(item
 * -> item.getId().equals(newPrestamoId)).findFirst().orElse(null);
 * assertNotNull(prestamo); assertEquals(NEW_PRESTAMO_NAME, prestamo.getName());
 * }
 * 
 * @Test public void modifyWithExistIdShouldModifyPrestamo() {
 * 
 * PrestamoDto dto = new PrestamoDto(); dto.setName(NEW_PRESTAMO_NAME);
 * dto.setNationality(NEW_NATIONALITY);
 * 
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" +
 * MODIFY_PRESTAMO_ID, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);
 * 
 * PrestamoSearchDto searchDto = new PrestamoSearchDto();
 * searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
 * 
 * ResponseEntity<ResponsePage<PrestamoDto>> response =
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new
 * HttpEntity<>(searchDto), responseTypePage);
 * 
 * assertNotNull(response); assertEquals(TOTAL_PRESTAMOS,
 * response.getBody().getTotalElements());
 * 
 * PrestamoDto prestamo = response.getBody().getContent().stream() .filter(item
 * -> item.getId().equals(MODIFY_PRESTAMO_ID)).findFirst().orElse(null);
 * assertNotNull(prestamo); assertEquals(NEW_PRESTAMO_NAME, prestamo.getName());
 * assertEquals(NEW_NATIONALITY, prestamo.getNationality()); }
 * 
 * @Test public void modifyWithNotExistIdShouldThrowException() {
 * 
 * long prestamoId = TOTAL_PRESTAMOS + 1;
 * 
 * PrestamoDto dto = new PrestamoDto(); dto.setName(NEW_PRESTAMO_NAME);
 * 
 * ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port +
 * SERVICE_PATH + "/" + prestamoId, HttpMethod.PUT, new HttpEntity<>(dto),
 * Void.class);
 * 
 * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()); }
 * 
 * @Test public void deleteWithExistsIdShouldDeleteCategory() {
 * 
 * long newPrestamosSize = TOTAL_PRESTAMOS - 1;
 * 
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" +
 * DELETE_PRESTAMO_ID, HttpMethod.DELETE, null, Void.class);
 * 
 * PrestamoSearchDto searchDto = new PrestamoSearchDto();
 * searchDto.setPageable(new PageableRequest(0, TOTAL_PRESTAMOS));
 * 
 * ResponseEntity<ResponsePage<PrestamoDto>> response =
 * restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, new
 * HttpEntity<>(searchDto), responseTypePage);
 * 
 * assertNotNull(response); assertEquals(newPrestamosSize,
 * response.getBody().getTotalElements()); }
 * 
 * @Test public void deleteWithNotExistsIdShouldThrowException() {
 * 
 * long deletePrestamoId = TOTAL_PRESTAMOS + 1;
 * 
 * ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port +
 * SERVICE_PATH + "/" + deletePrestamoId, HttpMethod.DELETE, null, Void.class);
 * 
 * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()); }
 * 
 * ParameterizedTypeReference<List<PrestamoDto>> responseTypeList = new
 * ParameterizedTypeReference<List<PrestamoDto>>() { };
 * 
 * @Test public void findAllShouldReturnAllPrestamo() {
 * 
 * ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(LOCALHOST
 * + port + SERVICE_PATH, HttpMethod.GET, null, responseTypeList);
 * 
 * assertNotNull(response); assertEquals(TOTAL_PRESTAMOS,
 * response.getBody().size()); } }
 * 
 */