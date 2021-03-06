USE [master]
GO
/****** Object:  Database [YellowMoonShop]    Script Date: 10/14/2020 11:28:55 PM ******/
CREATE DATABASE [YellowMoonShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'YellowMoonShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\YellowMoonShop.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'YellowMoonShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\YellowMoonShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [YellowMoonShop] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [YellowMoonShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [YellowMoonShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [YellowMoonShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [YellowMoonShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [YellowMoonShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [YellowMoonShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [YellowMoonShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [YellowMoonShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [YellowMoonShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [YellowMoonShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [YellowMoonShop] SET RECOVERY FULL 
GO
ALTER DATABASE [YellowMoonShop] SET  MULTI_USER 
GO
ALTER DATABASE [YellowMoonShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [YellowMoonShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [YellowMoonShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [YellowMoonShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [YellowMoonShop] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'YellowMoonShop', N'ON'
GO
USE [YellowMoonShop]
GO
/****** Object:  Table [dbo].[Cake]    Script Date: 10/14/2020 11:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cake](
	[cakeId] [char](10) NOT NULL,
	[cakeName] [varchar](50) NOT NULL,
	[cakePrice] [int] NOT NULL,
	[cakeDescription] [varchar](max) NOT NULL,
	[cakeQuantity] [int] NOT NULL,
	[cakeImage] [varchar](max) NULL,
	[cakeCreateDate] [datetime] NOT NULL,
	[cakeExpiredDate] [datetime] NOT NULL,
	[status] [varchar](10) NOT NULL,
	[categoryId] [varchar](10) NOT NULL,
 CONSTRAINT [PK_Cake] PRIMARY KEY CLUSTERED 
(
	[cakeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/14/2020 11:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[idCakeCategory] [varchar](10) NOT NULL,
	[categoryName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[idCakeCategory] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[History]    Script Date: 10/14/2020 11:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[History](
	[historyId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [varchar](10) NOT NULL,
	[cakeId] [char](10) NOT NULL,
	[date] [varchar](50) NOT NULL,
 CONSTRAINT [PK_History] PRIMARY KEY CLUSTERED 
(
	[historyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Order1]    Script Date: 10/14/2020 11:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Order1](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [varchar](10) NULL,
	[orderDate] [datetime] NOT NULL,
	[orderPrice] [int] NOT NULL,
	[userName] [varchar](10) NULL,
	[address] [varchar](max) NULL,
	[phone] [varchar](10) NULL,
	[paymentMethod] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 10/14/2020 11:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[orderDetailId] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [int] NOT NULL,
	[cakeId] [char](10) NOT NULL,
	[quantity] [int] NOT NULL,
	[detailPrice] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[orderDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 10/14/2020 11:28:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[userId] [varchar](10) NOT NULL,
	[password] [varchar](10) NOT NULL,
	[userName] [varchar](50) NOT NULL,
	[address] [varchar](max) NOT NULL,
	[role] [varchar](10) NOT NULL,
	[phone] [varchar](10) NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'AA0001    ', N'Green Earth', 20, N'Love the Earth', 4, N'img9', CAST(N'2020-06-23 00:00:00.000' AS DateTime), CAST(N'2020-08-12 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0006')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'AA0002    ', N'Old but Gold', 300, N'Love the Traditional', 5, N'img10', CAST(N'2020-07-07 00:00:00.000' AS DateTime), CAST(N'2020-08-08 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0006')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'AA0003    ', N'Time to say', 800, N'Love the Pink', 45, N'img11', CAST(N'2020-06-12 00:00:00.000' AS DateTime), CAST(N'2020-09-08 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0001')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'AA0004    ', N'Simply Love You', 1000, N'Love the Love', 12, N'img12', CAST(N'2020-01-02 00:00:00.000' AS DateTime), CAST(N'2020-02-01 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0002')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'AA0005    ', N'Not like a moon cake', 200, N'Love the other cake', 11, N'img13', CAST(N'2020-10-11 00:00:00.000' AS DateTime), CAST(N'2020-10-12 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0003')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'AB0001    ', N'Trung Thu', 150, N'Nhin giong rau cau', 11, N'img14', CAST(N'2020-12-12 00:00:00.000' AS DateTime), CAST(N'2020-12-13 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0006')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'AD0001    ', N'Chinese One', 1000, N'Yummy Yummy', 1, N'img8', CAST(N'2020-03-31 00:00:00.000' AS DateTime), CAST(N'2020-04-21 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0004')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'BC0001    ', N'Black Moon', 250, N'Nay Cung Ngon', 4, N'img5', CAST(N'2020-09-09 00:00:00.000' AS DateTime), CAST(N'2020-09-08 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0004')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'BC0002    ', N'Little Sweet', 50, N'Yummy Yummy', 100, N'img6', CAST(N'2020-08-20 00:00:00.000' AS DateTime), CAST(N'2020-09-01 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0004')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'BT0001    ', N'Beutifulsm', 5000, N'Great', 1, N'img15', CAST(N'2020-11-11 00:00:00.000' AS DateTime), CAST(N'2020-11-12 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0003')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'CC0009    ', N'"Trung Hoa"', 700, N'Just moon Cake', 2, N'img20', CAST(N'2020-09-02 00:00:00.000' AS DateTime), CAST(N'2020-09-03 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0003')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'D0001     ', N'Banh trung thu Nhan Tahp Cam', 100, N'Ngon lam', 3, N'img1', CAST(N'2020-10-15 00:00:00.000' AS DateTime), CAST(N'2020-10-30 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0001')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'DHS0001   ', N'MoonLover', 200, N'eat Deo cake, Deo ca nam', 14, N'img2', CAST(N'2020-10-01 00:00:00.000' AS DateTime), CAST(N'2020-10-02 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0002')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'DX0001    ', N'Peaceful', 300, N'delicious', 1, N'img3', CAST(N'2020-10-01 00:00:00.000' AS DateTime), CAST(N'2020-10-11 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0003')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'FN0001    ', N'The last Cake', 120, N'The last one', 6, N'img21', CAST(N'2020-08-08 00:00:00.000' AS DateTime), CAST(N'2020-08-20 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0001')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'MT0010    ', N'For Child', 700, N'A special gift', 4, N'img17', CAST(N'2020-08-16 00:00:00.000' AS DateTime), CAST(N'2020-08-18 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0004')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'SQ0001    ', N'Square', 500, N'Moon Cake look like banh chung', 7, N'img18', CAST(N'2020-04-30 00:00:00.000' AS DateTime), CAST(N'2020-05-01 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0006')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'TC0001    ', N'All in one like your family', 400, N'eat thap cam moon cake, forever happy', 10, N'img4', CAST(N'2020-07-24 00:00:00.000' AS DateTime), CAST(N'2020-09-26 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0001')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'TD0004    ', N'Traditional moon cake', 400, N'Family garther around', 4, N'img16', CAST(N'2020-07-23 00:00:00.000' AS DateTime), CAST(N'2020-09-30 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0005')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'TT0008    ', N'SingeOlogy', 800, N'Loneliness with Cake', 4, N'img19', CAST(N'2020-05-15 00:00:00.000' AS DateTime), CAST(N'2020-05-18 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0004')
INSERT [dbo].[Cake] ([cakeId], [cakeName], [cakePrice], [cakeDescription], [cakeQuantity], [cakeImage], [cakeCreateDate], [cakeExpiredDate], [status], [categoryId]) VALUES (N'YM0001    ', N'Little but Delicious', 500, N'Yummy Ymmy', 12, N'img7', CAST(N'2020-01-01 00:00:00.000' AS DateTime), CAST(N'2020-01-29 00:00:00.000' AS DateTime), N'ACTIVE', N'CC0005')
INSERT [dbo].[Category] ([idCakeCategory], [categoryName]) VALUES (N'CC0001', N'soft')
INSERT [dbo].[Category] ([idCakeCategory], [categoryName]) VALUES (N'CC0002', N'mixed')
INSERT [dbo].[Category] ([idCakeCategory], [categoryName]) VALUES (N'CC0003', N'green bean')
INSERT [dbo].[Category] ([idCakeCategory], [categoryName]) VALUES (N'CC0004', N'black')
INSERT [dbo].[Category] ([idCakeCategory], [categoryName]) VALUES (N'CC0005', N'fruit')
INSERT [dbo].[Category] ([idCakeCategory], [categoryName]) VALUES (N'CC0006', N'mixed cream')
SET IDENTITY_INSERT [dbo].[Order1] ON 

INSERT [dbo].[Order1] ([orderId], [userId], [orderDate], [orderPrice], [userName], [address], [phone], [paymentMethod]) VALUES (16, N'MB001', CAST(N'2020-10-14 21:20:35.010' AS DateTime), 500, NULL, NULL, NULL, N'COD (Cash on delivery)')
INSERT [dbo].[Order1] ([orderId], [userId], [orderDate], [orderPrice], [userName], [address], [phone], [paymentMethod]) VALUES (17, NULL, CAST(N'2020-10-14 21:20:52.677' AS DateTime), 200, N'Hieuthuba', N'Alabama str', N'909090909', N'COD (Cash on delivery)')
INSERT [dbo].[Order1] ([orderId], [userId], [orderDate], [orderPrice], [userName], [address], [phone], [paymentMethod]) VALUES (18, N'MB001', CAST(N'2020-10-14 21:41:36.907' AS DateTime), 300, NULL, NULL, NULL, N'COD (Cash on delivery)')
INSERT [dbo].[Order1] ([orderId], [userId], [orderDate], [orderPrice], [userName], [address], [phone], [paymentMethod]) VALUES (19, N'MB001', CAST(N'2020-10-14 21:42:49.977' AS DateTime), 300, NULL, NULL, NULL, N'COD (Cash on delivery)')
SET IDENTITY_INSERT [dbo].[Order1] OFF
INSERT [dbo].[Users] ([userId], [password], [userName], [address], [role], [phone]) VALUES (N'AD001', N'123', N'DinhNhuHieu', N'Pham The Hien p7 q8', N'admin', N'0335365325')
INSERT [dbo].[Users] ([userId], [password], [userName], [address], [role], [phone]) VALUES (N'AD002', N'123', N'HieuDinh', N'Hien Pham p7 q8', N'admin', N'0773367327')
INSERT [dbo].[Users] ([userId], [password], [userName], [address], [role], [phone]) VALUES (N'MB001', N'123', N'TruongQuocLap', N'Ton Dan q4', N'customer', N'0909090909')
ALTER TABLE [dbo].[Cake]  WITH CHECK ADD  CONSTRAINT [FK_Cake_Category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([idCakeCategory])
GO
ALTER TABLE [dbo].[Cake] CHECK CONSTRAINT [FK_Cake_Category]
GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Cake] FOREIGN KEY([cakeId])
REFERENCES [dbo].[Cake] ([cakeId])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Cake]
GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_Users] FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_Users]
GO
ALTER TABLE [dbo].[Order1]  WITH CHECK ADD  CONSTRAINT [FK_Order_User] FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[Order1] CHECK CONSTRAINT [FK_Order_User]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Cake] FOREIGN KEY([cakeId])
REFERENCES [dbo].[Cake] ([cakeId])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Cake]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Order] FOREIGN KEY([orderId])
REFERENCES [dbo].[Order1] ([orderId])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Order]
GO
USE [master]
GO
ALTER DATABASE [YellowMoonShop] SET  READ_WRITE 
GO
