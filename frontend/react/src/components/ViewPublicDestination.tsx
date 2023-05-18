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
  Box,
  Modal
} from "@mui/material";
import AddToPhotosIcon from '@mui/icons-material/AddToPhotos';
import { Link } from "react-router-dom";
import ReadMoreIcon from "@mui/icons-material/ReadMore";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import AddIcon from "@mui/icons-material/Add";
// import { BACKEND_API_URL } from "../../constants";
import PersonAddAlt1Icon from '@mui/icons-material/PersonAddAlt1';
import { Destination } from "../Model/Destination";


export const DestinationsShowAll = () => {
    const [open, setOpen] = useState(false);

  const[loading, setLoading] = useState(true);
  const[destinations, setDestinations] = useState<Destination[]>([]);
  const [selectedImage, setSelectedImage] = useState<string | null>(null);

  // useEffect(() => {
  //   if (page === 1) getLocations();
  //   else setPage(1);
  // }, [pageSize]);

  useEffect(() => {
    setLoading(true);

    const fetchDestinations = () => {
      fetch(`http://localhost:8080/api/destinations`)
        .then((response) => response.json())
        .then((data) => {
          setDestinations(data);
          setLoading(false);
        });
      
    };
    fetchDestinations();
  }, []);
  const handleClose = () => {
    setOpen(false);
  };
    

//   const sortClients = () => {
//       const sortedClients = [...destinations].sort((a: ClientsDTO, b: ClientsDTO) => {
//           if (a.lname < b.lname) {
//               return -1;
//           }
//           if (a.lname > b.lname) {
//               return 1;
//           }
//           return 0;
//       })
//       console.log(sortedClients);
//       setDestinations(sortedClients);
//   }
  
  return (
  <Container>
      <h1 style={{marginTop:"65px"}}>All Destinations</h1>

      {loading && <CircularProgress />}

      {!loading && destinations.length == 0 && <div>No clients found</div>}

      {!loading && (
          <IconButton component={Link} sx={{ mr: 3 }} to={`/addDestination`}>
              <Tooltip title="Add a new public destination" arrow>
                  <PersonAddAlt1Icon style={{color:"whitesmoke", fontSize:"50px"}} />
              </Tooltip>
          </IconButton>

      )}

      {/* {!loading && (
              <Button sx={{color:"black"}} onClick={sortClients} >
                  Sort clients
              </Button>
          )} */}

      {!loading && destinations.length > 0 && (
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
                      {destinations.map((destination:Destination, index) => (
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
                                    {/* <img
                                    //src={destination.image}
                                    alt={destination.title}
                                    // style={{
                                    //     display: 'block',
                                    //     margin: 'auto',
                                    //     maxWidth: '90vw',
                                    //     maxHeight: '90vh',
                                    //   }}
                                    onClick={() => {
                                        setSelectedImage(destination.image);
                                        setOpen(true);
                                    }}
                                    />
                                    <Modal
                                    open={!!destination.image}
                                    onClose={handleClose}
                                    sx={{
                                        display: "flex",
                                        alignItems: "center",
                                        justifyContent: "center"
                                    }}
                                    >
                                    <img
                                        src={destination.image}
                                        alt={destination.title}
                                        style={{ maxWidth: "90vw", maxHeight: "90vh" }}
                                    />
                                    </Modal>
                                </TableCell> */}
                              <TableCell align="center">

                  <IconButton component={Link} sx={{ mr: 3 }} to={`/destination/${destination.destinationId}/addprivate`}>
                    <AddToPhotosIcon />
                  </IconButton>

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