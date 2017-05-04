# Design Pattern

Contents:

- [Types of Design Patterns](#Types of Design Patterns)
- [Factory Pattern](#Factory Pattern)
- [Singleton Pattern](#Singleton Pattern)
- [MVC Pattern](#MVC Pattern)

<a name="Types of Design Patterns" />

## Types of Design Patterns
+ Creational Patterns
  - hide creation logic
+ Structural Patterns
  - class and object composition
+ Behavioral Patterns
  - communication between objects
+ J2EE Patterns


<a name="Factory Pattern" />

## Factory Pattern
+ create an interface

``` 
public interface Shape{
	 void draw();
}
```

+ create concrete classes

``` 
  public class Rectangle implements Shape{
	   @Override
	   public void draw(){
	      System.out.println("Inside Rectangle");
       }
   }
```

``` 
    public class Circle implements Shape{
	   @Override
	   public void draw(){
	      System.out.println("Inside Circle");
       }
    }
```

+ create factory to generate concrete classes

``` 
    public class ShapeFactory{
	   public Shape getShape(String type){
	      if(type == null){
	         return null;   
	      }
	      if(shape.equalsIgnoreCase("Rectangle")){
             return new Rectangle();
	      }
	      if(shape.equalsIgnoreCase("Circle")){
             return new Circle();
	      }
	      return null
	   }
    }
```

+ demo

``` 
    public class Demo{
	   ShapeFactory factory = new ShapeFactory();
	   Shape circle = factory.getShape("Circle");
	   circle.draw();
	   Shape rectangle = factory.getShape("Rectangle");
	   rectangle.draw();
    }
```

<a name="Singleton Pattern"/>

## Singleton Pattern
+ create singleton class

``` 
    public class SingleObject{
	   private static SingleObject ins = new SingleObject();

	   private SingleObject(){}

	   public static SingleObject getInstance(){
	   	   return ins;
	   }

	   public void show(){
	   	   System.out.println("Hello");
	   }
    }
```

+ demo

``` 
    public class Demo{
	   public static void main(String[] args){
	       SingleObject obj = SingleObject.getInstance();
	       obj.show();
	   }
    }
```

<a name="MVC Pattern" />

## MVC Pattern
+ Model-View-Controller
  - Model: represent object, can have logic to update controller if its data changes
  - View: represent visualization of data from Model
  - Controller: act on Model and View, control data flow into Model and update View when data changes

+ Structure
  - app: models, views, controllers
  - config: global server configurations
  - lib: assorted libraries

+ Procedure
  - server routes the request to certain controller
  - controller interprets the request, load reqested information from models
  - controller passes information from model to view
  - final view is sent to user

+ Model

``` 
   public class Student{
       private String id;
       private String name;

       public String getId(){
           return id;
       }

       public String getName(){
           return name;
       }

       public void setId(String id){
           this.id = id;
       }

       public void setName(String name){
           this.name = name;
       }	
    }
```

+ View

``` 
    public class StudentView{
	   public void printInfo(String studentName, String studentId){
	        System.out.println("Student: " + studentName);
	        System.out.println("Student ID: " + studentId);
	   }
    }
```

+ Controller

``` 
   public class StudentController{
	   private Student model;
	   private StudentView view;

	   public StudentController(Student model, StudentView view){
	   	   this.model = model;
	   	   this.view = view;
	   }

	   public void setStudentName(String name){
	   	   model.setName(name);
	   }

	   public void setStudentId(String id){
	   	   model.setId(id);
	   }

	   public String getStudentName(){
	   	   return model.getName();
	   }

	   public String getStudentId(){
	   	   return model.getId();
	   }

	   public void updateView(){
	   	   view.printInfo(model.getName(), model.getId());
	   }
    }
```

+ Demo

``` 
   public class Demo{
	  public static void main(String[] args){
	      Student model = getStudentFromDatabase();
	      StudentView view = new StudentView();
	      StudentController controller = new StudentController(model,view);

	      controller.updateView();

	      controller.setStudentName("Other name");
	      controller.updateView();
	  }
    }
```















