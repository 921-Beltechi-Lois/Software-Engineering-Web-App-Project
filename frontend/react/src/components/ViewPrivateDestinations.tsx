import { useEffect, useState } from "react"; 
import {
	TableContainer,
	Paper,
	Table,
	TableHead,
	TableRow,
	TableCell,
	TableBody,
	CircularProgress,
	Container,
	IconButton,
	Tooltip,
  Button,
  Box
} from "@mui/material";
import AddToPhotosIcon from '@mui/icons-material/AddToPhotos';
import { Link, useLocation } from "react-router-dom";
import ReadMoreIcon from "@mui/icons-material/ReadMore";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import AddIcon from "@mui/icons-material/Add";
// import { BACKEND_API_URL } from "../../constants";
import PersonAddAlt1Icon from '@mui/icons-material/PersonAddAlt1';
import { Destination } from "../Model/Destination";
import { AppMenu } from "./AppMenu";
import { Router } from "@mui/icons-material";


export const DestinationsShowAllPrivate = () => {
    const [loading, setLoading] = useState(true);
    const [privateDestinations, setPrivateDestinations] = useState<Destination[]>([]);
  
    const location = useLocation();
const { loggedInUsername: username } = location.state;

useEffect(() => {
  setLoading(true);

  const fetchPrivateDestinations = () => {
    fetch(`http://localhost:8080/api/userdestinations/${username}`)
      .then((response) => response.json())
      .then((data) => {
        setPrivateDestinations(data);
        setLoading(false);
      });
  };

  fetchPrivateDestinations();
}, [username]);
  
    return (
        
      <Container>
        <AppMenu />
        <h1 style={{ marginTop: "65px" }}>All Private Destinations</h1>
  
        {loading && <CircularProgress />}
  
        {!loading && privateDestinations.length == 0 && <div>No private destinations found</div>}
  
        {/* {!loading && (
          <IconButton component={Link} sx={{ mr: 3 }} to={`/addPrivateDestination`}>
            <Tooltip title="Add a new private destination" arrow>
              <PersonAddAlt1Icon style={{ color: "whitesmoke", fontSize: "50px" }} />
            </Tooltip>
          </IconButton>
        )} */}
  
        {!loading && privateDestinations.length > 0 && (
          <>
          <TableContainer component={Paper}>
              <Table sx={{ minWidth: 1200 }} aria-label="table" style={{backgroundColor:"whitesmoke"}}>
                  <TableHead>
                      <TableRow>
                      <TableCell align="center" style={{color:"#2471A3", fontWeight:'bold'}}>#</TableCell>
                          <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Title</TableCell>
                          <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Geographical location</TableCell>
                          <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Description</TableCell>
                          <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Image</TableCell>
                          <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Operations</TableCell>

                      </TableRow>
                  </TableHead>
                  <TableBody>
                      {privateDestinations.map((destination:Destination, index) => (
                          <TableRow key={destination.destinationId}>
                              <TableCell component="th" scope="row">
                                  {index}
                              {/* <TableCell component="th" scope="row">
                                  <Link to={`/clients/${destination.idClient}/details`} title="View clients details">
                                      {"view details"}
                                      
                                  </Link>

                              </TableCell> */}
                              </TableCell>
                              <TableCell align="center">{destination.title}</TableCell>
                              <TableCell align="center">{destination.geo_location}</TableCell>
                              <TableCell align="center">{destination.description}</TableCell>
                              <TableCell align="center">{destination.image}</TableCell>
                              <TableCell align="center">

                  {/* <IconButton component={Link} sx={{ mr: 3 }} to={`/destination/${destination.destinationId}/addprivate`}>
                    <AddToPhotosIcon />
                  </IconButton> */}

                  <IconButton component={Link} sx={{ mr: 3 }} to={`/destinations/${destination.destinationId}/delete`}>
                    <DeleteForeverIcon sx={{ color: "red" }} />
                  </IconButton>
                </TableCell>
                          </TableRow>
                      ))}
              </TableBody>
              </Table>
          </TableContainer>
          
        </>
      )
      }
  </Container>
      
  );       
};