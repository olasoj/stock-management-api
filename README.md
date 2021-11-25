![Logo](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/th5xamgrr6se0x5ro4g6.png)

# ActiveEdge Software Developer Exercise

Design and code a simple Java Spring application that manages stock

Write a simple and efficient Java >= 8 program that returns the smallest non-occurring
integer in a given Array

## Authors

- [@olasoj](https://www.github.com/olasoj)

## 🚀 About Me

A highly experienced, results-oriented computer scientist with a proven track record of managing all aspects of the Software Development Life Cycle, from analysis to design to execution and maintenance. Proficient in a wide range of programs and technologies required to develop high-quality, cost-effective applications and systems that improve organizational efficiency and productivity.

## Features

**The following functionalities are available in this stock management application:**

- Users can request stock information by passing the stock id. Provided the stock exists.
- Users can create, update or delete a stock.
- Users can retrieve all stocks.

## Environment Variables

This project made no use of environmental variables.

## API Reference

#### Get Stock Details

```http
  GET /api/stocks/{stockId}
```

| Parameter | Type   | Description            |
| :-------- | :----- | :--------------------- |
| `stockId` | `long` | **Required**. Stock Id |

#### POST New Stock

```http
  POST /api/stocks/{stockId}
```

#### GET All Stocks

```http
  POST /api/stocks
```

#### PUT update existing stock

```http
  PUT /api/stocks/{stockId}
```

| Parameter | Type   | Description            |
| :-------- | :----- | :--------------------- |
| `stockId` | `long` | **Required**. Stock Id |

#### DELETE delete existing stock

```http
  DELETE /api/stocks/{stockId}
```

| Parameter | Type   | Description            |
| :-------- | :----- | :--------------------- |
| `stockId` | `long` | **Required**. Stock Id |

## Run Locally

Clone the project

```bash
   git clone -b main https://github.com/olasoj/stock-management-api.git
```

Go to the project directory

```bash
  cd stock-management-api
```

Start the stock-management-api

```bash
    mvn spring-boot:run
```

## Process Flow

Question 1

![DeleteStock](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgRGVsZXRlIFN0b2NrIEluZm9ybWF0aW9uCgphY3RvciBVc2VyClVzZXItPitQb3N0bWFuOgAoDQoKABAHLT4rAEAGQ29udHJvbGxlcgAbDwAPEAAqCFNlcnZpY2UAGhQAFActAFUIUmVwb3NpdG9yeTogR2V0AHsICgphbAAGByBOb3QgRm91bmQKICAgAIFTBgAsCi0tPgBlDgAbGQBwCgCBRBIAUBkAgXELIC0tPi0AgikJAIELEAplbHMAgm8OAIEaFCAAgR4RUmV0cmlldgAjEQCCHgotAIIXEQCDUhEAgVEUAIMpE3Jlc3BvbnNlAIE8IwAeDQCCcwUKZW5kCgo&s=default)
![UpdateStock](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgVXBkYXRlIFN0b2NrIEluZm9ybWF0aW9uCgphY3RvciBVc2VyClVzZXItPitQb3N0bWFuOgAoDQoKABAHLT4rAEAGQ29udHJvbGxlcgAbDwAPEAAqCFNlcnZpY2UAGhQAFActAFUIUmVwb3NpdG9yeTogR2V0AHsICgphbAAGByBOb3QgRm91bmQKICAgAIFTBgAsCi0tPgBlDgAbGQBwCgCBRBIAUBkAgXELIC0tPi0AgikJAIELEAplbHMAgmoTAIEfFCAAgSMRUmV0cmlldgCCeQgAgRsYAIJCFQBYDwCCUBAAgzANcmVzcG9uc2UAgT0jAB4NAIJ0BQplbmQKCg&s=default)
![Get Stock Info](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgR2V0IFN0b2NrIEluZm9ybWF0aW9uCgphY3RvciBVc2VyClVzZXItPitQb3N0bWFuOgAnC0RldGlhbHMKCgAVBy0-KwBFBkNvbnRyb2xsZXIAGxQAFBAALwhTZXJ2aWNlABoZABkHLQBfCFJlcG9zaXRvcnkAgQAVCgphbACBVAhOb3QgRm91bmQKICAgAIFqBgA0CgBICQB4CQAcGQBzEACBXQ0AUhkAggULIC0tPi0AgkIJAIENEAplbHNlIFJldHVybgCDCwsAgSEUIACBJBJzAIM-CS4AgWIFAIEQJwAxBmkAWw0AgRkZAB4PAGIGZW5kCg&s=default)
![CreateStock](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgQ3JlYXRlIFN0b2NrCgphY3RvciBVc2VyClVzZXItPitQb3N0bWFuOgAaDwAQBy0-KwA1BSBDb250cm9sbGVyABsPAA8QACoIU2VydmljZQBIEAoKYWx0IFZhbGlkYXRpb24gRXJyb3IKICAgAIEdBgAwBy0tPi0AZxIAHRoAgRULIC0tPi0AgU0JAFkRCmVscwCCBw8AahMAgXAGUmVwb3NpdG9yeQCCEA8AgSYJABgKLS0-LQCBNg06AIESCACCfAVkAEwaAIJPDXJlc3BvbnNlAIE4IwAeDQCCPQUKZW5kCgo&s=default)
![GetAllStocks](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgR2V0IEFsbCBTdG9ja3MKCmFjdG9yIFVzZXIKVXNlci0-K1Bvc3RtYW46ABoRABIHLT4rADgFIENvbnRyb2xsZXIAGxEAERAALAhTZXJ2aWNlABoWABYHLQBZCFJlcG9zaXRvcnkAShYAFgotLT4tAIFMBgBYCQBDFi0AgSgSAIEbFyAtLT4tAIICCQCCKAY&s=default)

Question 2

### input

- First, it was necessary to determine what data structure would be used to represent the Centiliters servers would use.

### Processing

- next was to determine the sequence in which the data would be updated.
- Then some rules were established: if the row is 0, we cannot decrement the row. If the row is in column 0, we cannot decrement the column. If the row is M-1, we cannot increment the row. If the row is in column 0, we cannot increment the column. If the row is M-0, we cannot increment the row. If the row is M-1, we cannot increment the row.
- Identified all common cases and defined behaviors for each case.
- The initial date was set to 0 and incremented if all servers were not updated, else the updated day would be returned.

### output

- The output returned is the total number of days it took to update all servers

## Documentation

[Documentation](https://stock-management-doc.surge.sh/)

## Tech Stack

**Client:** Postman

**Server:** Java, Spring boot
