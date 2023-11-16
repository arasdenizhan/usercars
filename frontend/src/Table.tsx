import * as React from 'react';
import axios from "axios";
import {useEffect, useState} from "react";
import {DataGrid} from "@mui/x-data-grid";

const columns = [
    { field: 'id', headerName: 'Car Id', width:100 },
    { field: 'name', headerName: 'User Name', width:300 },
    { field: 'user_id', headerName: 'User Id', width:100 },
    { field: 'car_make', headerName: 'Car Make', width:100 },
    { field: 'car_model', headerName: 'Car Model', width:100 },
    { field: 'car_number_plate', headerName: 'Number Plate', width:100 }
];

type usersDto= {
    id: number,
    name: string;
    cars: carsDto[];
};

type carsDto= {
    id: number;
    make: string;
    model: string;
    numberPlate: string;
};

class TableType {
    public id: number;
    public name: string;
    public user_id: number;
    public car_make: string;
    public car_model: string;
    public car_number_plate: string;
}

async function getUserCarsList() {
    let result = new Array<TableType>();
    await axios.get('http://localhost:8080/usercars/v1/users')
        .then(function (response) {
            response.data.forEach(usersDto => {
                usersDto.cars.forEach(carsDto => {
                    let tempObj = new TableType();
                    tempObj.id = carsDto.id
                    tempObj.name = usersDto.name
                    tempObj.user_id = usersDto.id
                    tempObj.car_make = carsDto.make
                    tempObj.car_model = carsDto.model
                    tempObj.car_number_plate = carsDto.numberPlate
                    result = [...result, tempObj]
                })
            })
        })
        .catch(function (error) {
            if (!error.response) {
                alert("Network error - Back-end can be not working");
            } else {
                console.log(error)
                alert(error.message.concat(" | ").concat(error.response.data))
            }
        });
    return result
}

export default function UserCarTable() {
    const [rows, setRows] = useState([]);
    useEffect(() => {
        getUserCarsList().then(resp => setRows(resp))
    }, []);
    return (
        <div className="PageDiv">
            <DataGrid
                rows={rows}
                columns={columns}
                pageSize={10}
                pageSizeOptions={[10]}
            />
        </div>
    );
}
