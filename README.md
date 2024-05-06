## How to run SQL server with docker / How to run backend project

1. Run docker

2. ```bash
   docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=NewPassword123!' -p 1433:1433 --name sql_server_container -d mcr.microsoft.com/mssql/server:2019-latest
   ```

3. Login DB (Server name: localhost, Authentication: SQL Server Authentication, Login: sa, Password: NewPassword123!)

4. Create new DB (name: SI)

5. Run SQL script to create tables

6. Change DB string in application.properties: spring.datasource.url=jdbc:sqlserver://localhost;databaseName=SI;integratedSecurity=false;user=sa;password=NewPassword123!;encrypt=false;

7. ```bash
   mvn clean install
   ```
   
8. ```bash
   java -jar target/projekt-0.0.1-SNAPSHOT.jar
   ```



 ### SQL Script: 
 ```bash
   USE [SI]
GO
/****** Object:  User [admin]    Script Date: 6.5.2024. 1:56:19 ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [admin]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [admin]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [admin]
GO
ALTER ROLE [db_datareader] ADD MEMBER [admin]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [admin]
GO
/****** Object:  Table [dbo].[user]    Script Date: 6.5.2024. 1:56:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[first_name] [varchar](50) NOT NULL,
	[last_name] [varchar](50) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[city] [varchar](100) NOT NULL,
	[address] [varchar](100) NOT NULL,
	[password] [varchar](100) NOT NULL,
	[date_created] [datetime] NOT NULL,
	[date_modified] [datetime] NOT NULL,
	[user_type] [int] NOT NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_type]    Script Date: 6.5.2024. 1:56:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_user_type] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
   ```
