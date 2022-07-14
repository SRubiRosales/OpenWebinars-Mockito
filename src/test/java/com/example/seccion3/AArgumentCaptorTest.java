package com.example.seccion3;

import com.example.demo.observer.Weather;
import com.example.demo.observer.WeatherObserver;
import com.example.demo.observer.WeatherType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AArgumentCaptorTest {
    @Mock
    private WeatherObserver weatherObserver;//Mock
    //Class under test
    @InjectMocks//Inyeccion de dependencia
    private Weather weather;//Clase a testear

    @Test
    void test1() {
        weather.addObserver(weatherObserver);
        weather.changeWeather(WeatherType.RAINY);
        ArgumentCaptor<WeatherType> argumentCaptor = ArgumentCaptor.forClass(WeatherType.class);
        verify(weatherObserver).update(argumentCaptor.capture());//Captura el argumento que se envia al metodo
        assertEquals(WeatherType.CLOUDY, argumentCaptor.getValue());//Obtiene el dato enviado como parametro
    }

    @Test
    void test2() {
        weather.addObserver(weatherObserver);
        weather.changeWeather(WeatherType.RAINY);
        weather.changeWeather(WeatherType.SUNNY);
        ArgumentCaptor<WeatherType> argumentCaptor = ArgumentCaptor.forClass(WeatherType.class);
        verify(weatherObserver, times(2)).update(argumentCaptor.capture());
        List<WeatherType> types = argumentCaptor.getAllValues();//Obtiene todos los datos enviados como parametros
        assertEquals(WeatherType.RAINY, types.get(0));
        assertEquals(WeatherType.SUNNY, types.get(1));
    }
}
