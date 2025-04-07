package com.example.demo.repository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ServiciosInformes<T> {
    public Integer cantidadActual();
    public List<T> imprimirListado();
}
