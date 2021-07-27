/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     26/7/2021 23:37:50                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('RESERVACION') and o.name = 'FK_RESERVAC_USUARIO_R_USUARIO')
alter table RESERVACION
   drop constraint FK_RESERVAC_USUARIO_R_USUARIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('RESERVACION') and o.name = 'FK_RESERVAC_USUARIO_R_RESTAURA')
alter table RESERVACION
   drop constraint FK_RESERVAC_USUARIO_R_RESTAURA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RESERVACION')
            and   name  = 'USUARIO_RESTAURANTE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index RESERVACION.USUARIO_RESTAURANTE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RESERVACION')
            and   name  = 'USUARIO_RESTAURANTE_FK'
            and   indid > 0
            and   indid < 255)
   drop index RESERVACION.USUARIO_RESTAURANTE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RESERVACION')
            and   type = 'U')
   drop table RESERVACION
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RESTAURANTE')
            and   type = 'U')
   drop table RESTAURANTE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('USUARIO')
            and   type = 'U')
   drop table USUARIO
go

/*==============================================================*/
/* Table: RESERVACION                                           */
/*==============================================================*/
create table RESERVACION (
   ID_USUARIO           int                  not null,
   ID_RESTAURANTE       int                  not null,
   FECHA_RESERVACION    datetime             null,
   HORA_RESERVACION     datetime             null,
   NUM_PERSONAS         int                  null,
   constraint PK_RESERVACION primary key (ID_USUARIO, ID_RESTAURANTE)
)
go

/*==============================================================*/
/* Index: USUARIO_RESTAURANTE_FK                                */
/*==============================================================*/
create index USUARIO_RESTAURANTE_FK on RESERVACION (
ID_USUARIO ASC
)
go

/*==============================================================*/
/* Index: USUARIO_RESTAURANTE2_FK                               */
/*==============================================================*/
create index USUARIO_RESTAURANTE2_FK on RESERVACION (
ID_RESTAURANTE ASC
)
go

/*==============================================================*/
/* Table: RESTAURANTE                                           */
/*==============================================================*/
create table RESTAURANTE (
   ID_RESTAURANTE       int                  not null,
   NOMBRE_RESTAURANTE   varchar(50)          null,
   FOTO_RESTAURANTE     image                null,
   NUMERO_RESTAURANTE   int                  null,
   CELULAR_RESTAURANTE  int                  null,
   CORRE_ELECTRONICO_RESTAURANTE varchar(50)          null,
   DIRECCION_RESTAURANTE varchar(200)         null,
   DESCRIPCION_RESTAURANTE varchar(50)          null,
   constraint PK_RESTAURANTE primary key nonclustered (ID_RESTAURANTE)
)
go

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   ID_USUARIO           int                  not null,
   FOTO_USUARIO         image                null,
   CORREO_ELECTRONICO_USUARIO varchar(100)         null,
   NOMBRE_USUARIO       varchar(50)          null,
   APELLIDO_USUARIO     varchar(50)          null,
   NOMBRE_IDENTIFICADOR_USUARIO varchar(50)          null,
   FECHA_NACIMIENTO_USUARIO datetime             null,
   DESCRIPCION_USUARIO  varchar(200)         null,
   constraint PK_USUARIO primary key nonclustered (ID_USUARIO)
)
go

alter table RESERVACION
   add constraint FK_RESERVAC_USUARIO_R_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
go

alter table RESERVACION
   add constraint FK_RESERVAC_USUARIO_R_RESTAURA foreign key (ID_RESTAURANTE)
      references RESTAURANTE (ID_RESTAURANTE)
go

