 # springBoot_Project

 
springBoot_BackEnd
------------------
- CRUD
- Entity, Model, Service, Controller
- Model Mapper (Direct with one entity OR two entity to one)
- Various relationship between tables: @ManyToMany, @OneToMany, @ManyToOne
- Commit and RollBack using @Transactional
- for removing circular reference we are:
  - working with models instead of entuties that are converted to models using modelMappe
  - using @EqualsAndHashCode(exclude = {"ourSet"}) to override @Data's @EqualsAndHashCode
  - using @JsonBackReference in child model and @JsonManagedReference in parent model
