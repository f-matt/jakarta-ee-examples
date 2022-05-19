package com.github.fmatt.service;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import com.github.fmatt.model.Bar;
import com.github.fmatt.model.Foo;

@Stateless
public class FooService {

    public List<Foo> getFoos() {
        Bar bar11 = new Bar(11, "Bar 11");
        Bar bar12 = new Bar(12, "Bar 12");
        Bar bar13 = new Bar(13, "Bar 13");
        Foo foo1 = new Foo(1, "Foo 1", Arrays.asList(bar11, bar12, bar13));
        
        Bar bar21 = new Bar(21, "Bar 21");
        Bar bar22 = new Bar(22, "Bar 22");
        Bar bar23 = new Bar(23, "Bar 23");
        Foo foo2 = new Foo(2, "Foo 2", Arrays.asList(bar21, bar22, bar23));

        Bar bar31 = new Bar(31, "Bar 31");
        Bar bar32 = new Bar(32, "Bar 32");
        Bar bar33 = new Bar(33, "Bar 33");
        Foo foo3 = new Foo(3, "Foo 3", Arrays.asList(bar31, bar32, bar33));
 
        return Arrays.asList(foo1, foo2, foo3);
    }
    
}
