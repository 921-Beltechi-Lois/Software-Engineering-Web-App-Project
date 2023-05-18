import { Button, Card, CardActions, CardContent, IconButton, TextField, Typography } from "@mui/material";
import { Container } from "@mui/system";
import { useEffect, useState, useCallback, SetStateAction} from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { Destination } from "../Model/Destination";
import axios from "axios";
import Switch from "./Switch";
import { MDBFile } from 'mdb-react-ui-kit';




export const AddDestinationAdmin = () => {
	const navigate = useNavigate();

	const [destination, setDestinations] = useState<Destination>({
        destinationId:0,
        isPrivate:false,
        userId:0,
        title:"",
        geo_location:"",
        description:"",
        image:"",

    });

	// State variables for input field errors
	const [isPrivateError, setIsPrivateError] = useState(false);
	const [titleError, setTitleError] = useState(false);
	const [geoLocationError, setGeoLocationError] = useState(false);
	const [staydates, setStaydates] = useState(0);
	const [isPrivate, setIsPrivate] = useState(false);


	const handleStayDatesChange = (event: React.ChangeEvent<HTMLInputElement>) => {
		setStaydates(parseInt( event.target.value));
	  };

	  const handleToggle = () => {
		setIsPrivate(!isPrivate);
	  };


	const addDestination = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		// Check for input field errors
		if (destination.isPrivate == null) {
			setIsPrivateError(true);
			return;
			// Return to prevent submission if there is an error
		}
		if (destination.title === "") {
			setTitleError(true);
			return;
		}
		if (destination.geo_location === "") {
			setGeoLocationError(true);
			return;
		}
		try {
			await axios.post(`http://localhost:8080/api/destination/${staydates}`, destination);
			navigate("/destinations");
		} catch (error) {
			console.log(error);
		}
	};

	return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/destinations`}>
						<ArrowBackIcon />
					</IconButton>{" "}
					<form onSubmit={addDestination}>
					<div style={{ display: "flex", alignItems: "center", marginBottom: 20 }}>
             
              


            </div>

						{/* <TextField
							id="isPrivate"
							label="Is private?"
							variant="outlined"
							fullWidth
							sx={{ mb: 2 }}
							error={isPrivateError}
							helperText={isPrivateError ? "this option cannot be empty" : ""}
							onChange={(event) => {
								setDestinations({ ...destination, isPrivate: Boolean(event.target.value) });
								setIsPrivateError(false);
							}}
						/> */}
						<TextField
							id="title"
							label="Title"
							variant="outlined"
							fullWidth
							sx={{ mb: 2 }}
							error={titleError}
							helperText={titleError ? "Title cannot be empty" : ""}
							onChange={(event) => {
								setDestinations({ ...destination, title: event.target.value });
								setTitleError(false);
							}}
						/>

                        <TextField
							id="geo_location"
							label="Geographical Location"
							variant="outlined"
							fullWidth
							sx={{ mb: 2 }}
							error={geoLocationError}
							helperText={geoLocationError ? "City cannot be empty" : ""}
							onChange={(event) => {
								setDestinations({ ...destination, geo_location: event.target.value });
								setGeoLocationError(false);
							}}
						/>
						<TextField
							id="description"
							label="Description "
							variant="outlined"
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDestinations({ ...destination, description: event.target.value })}
						/>
						<div>
      <MDBFile disabled label='Disabled file input example' id='formFileDisabled' />
    </div>
						<Button type="submit">Add Destination</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
};