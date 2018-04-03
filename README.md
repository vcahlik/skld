# skld
Warehouse information system. Created as a team project at FIT CTU.  

My job was to program the whole GUI part of the client application. I implemented it with JavaFX using an extensive custom-designed architecture which slices the UI and logic into reusable MVC fragments. Each of these fragments does only one thing, such as: provide a form, show items in the database, show a toolbar, wrap content in a history browser that enables the user going one screen forward/backward... These small fragments are finally interconnected in a tree-like structure of the resulting application. Overally a robust solution which would IMO lead to easy maintainability and extensibility if used in a real-world project.
