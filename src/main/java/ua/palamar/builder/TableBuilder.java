package ua.palamar.builder;

import ua.palamar.repository.TableRepository;

import java.io.File;

public interface TableBuilder {

    String buildResultTable(File table, TableRepository tableRepository);

}
