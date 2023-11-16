import * as React from 'react';
import {useState} from "react";
import {TextField} from "@mui/material";
import Button from "@mui/material/Button";
import {DataGrid} from "@mui/x-data-grid";
import axios from "axios";

type carsDto= {
    id: number;
    make: string;
    model: string;
    numberPlate: string;
};

class TableType {
    public id: number;
    public user_id: number;
    public car_make: string;
    public car_model: string;
    public car_number_plate: string;
}

const columns = [
    { field: 'id', headerName: 'Car Id', width:100 },
    { field: 'user_id', headerName: 'User Id', width:100 },
    { field: 'car_make', headerName: 'Car Make', width:100 },
    { field: 'car_model', headerName: 'Car Model', width:100 },
    { field: 'car_number_plate', headerName: 'Number Plate', width:100 }
];

async function onClickSearch(carId) {
    let result = new Array<TableType>();
    await axios.get('http://localhost:8080/usercars/v1/cars/'.concat(carId))
        .then(function (response) {
            console.log(response)
            let carsDto = response.data
                let tempObj = new TableType();
                tempObj.id = carsDto.id
                tempObj.user_id = carsDto.userId
                tempObj.car_make = carsDto.make
                tempObj.car_model = carsDto.model
                tempObj.car_number_plate = carsDto.numberPlate
                result = [...result, tempObj]
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

export default function SearchCar() {
    const [rows, setRows] = useState([]);
    const [carId, setCarId] = useState("0");
    return (
        <div>
            <div className="SearchPageDiv">
                <div className="PageDiv">
                    <TextField id="filled-basic" label="Car Id" variant="filled" onChange={(v) => setCarId(v.target.value)}/>
                </div>
                <div className="PageDiv">
                    <Button variant="contained" onClick={() => onClickSearch(carId).then(resp => setRows(resp))}>Search</Button>
                </div>
            </div>
            <div className="PageDiv">
                <DataGrid
                    rows={rows}
                    columns={columns}
                    pageSize={10}
                    pageSizeOptions={[10]}
                />
            </div>
        </div>
    );
}
