/*
 * Copyright 2004-2007 H2 Group. Licensed under the H2 License, Version 1.0 (http://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.h2.samples;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;

/**
 * This sample application shows how to use the CSV tool
 * to write CSV (comma separated values) files, and
 * how to use the tool to read such files.
 */
public class CsvSample {
    public static void main(String[] args) throws Exception {
        CsvSample.write();
        CsvSample.read();
    }

    static void write() throws Exception {
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn("NAME", Types.VARCHAR, 255, 0);
        rs.addColumn("EMAIL", Types.VARCHAR, 255, 0);
        rs.addColumn("PHONE", Types.VARCHAR, 255, 0);
        rs.addRow(new String[] { "Bob Meier", "bob.meier@abcde.abc", "+41123456789" });
        rs.addRow(new String[] { "John Jones", "john.jones@abcde.abc", "+41976543210" });
        Csv.getInstance().write("data/test.csv", rs, null);
    }

    static void read() throws Exception {
        ResultSet rs = Csv.getInstance().read("data/test.csv", null, null);
        ResultSetMetaData meta = rs.getMetaData();
        while (rs.next()) {
            for (int i = 0; i < meta.getColumnCount(); i++) {
                System.out.println(meta.getColumnLabel(i + 1) + ": " + rs.getString(i + 1));
            }
            System.out.println();
        }
        rs.close();
    }
}