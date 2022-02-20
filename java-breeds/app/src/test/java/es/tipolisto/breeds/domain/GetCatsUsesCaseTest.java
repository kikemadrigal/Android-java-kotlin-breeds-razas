package es.tipolisto.breeds.domain;

import static org.junit.Assert.*;

import org.junit.Before;

import es.tipolisto.breeds.data.DataRepository;
import io.mockk.MockKAnnotations;
import io.mockk.impl.annotations.RelaxedMockK;

/**
 * Un test es muy similar a una función
 * junit nos provee de las anotaciones @Before que es lo que junit tiene que hacer antes de los test
 */
public class GetCatsUsesCaseTest {
    //La etiqueta relaxedMock: si no definimos una de las respuesta no la va a generar automáticamente
    //La etiqueta Mock: si no definimos las respuestas el test va a fallar
    @RelaxedMockK
    DataRepository dataRepository;
    GetCatsUsesCase getCatsUsesCase;
    //Antes inicializamos nuestra librería de mock
    @Before
    public void onBefore(){

    }

}