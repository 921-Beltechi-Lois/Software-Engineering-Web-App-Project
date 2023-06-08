import { useCallback, useEffect, useState } from "react";
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
  Modal,
} from "@mui/material";
import AddToPhotosIcon from "@mui/icons-material/AddToPhotos";
import { Link } from "react-router-dom";
import ReadMoreIcon from "@mui/icons-material/ReadMore";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import AddIcon from "@mui/icons-material/Add";
// import { BACKEND_API_URL } from "../../constants";
import PersonAddAlt1Icon from "@mui/icons-material/PersonAddAlt1";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";

import { Destination } from "../Model/Destination";
import axios from "axios";

export const DestinationsShowAll = () => {
  const [open, setOpen] = useState(false);

  const [loading, setLoading] = useState(true);
  const [destinations, setDestinations] = useState<Destination[]>([]);
  const [selectedImage, setSelectedImage] = useState<string | null>(null);
  const [isAdmin, setAdmin] = useState(true);

  const fetchAdminStatus = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/isAdmin");
      const isAdmin = response;
      setAdmin(isAdmin.data);
      console.log("Admin=" + isAdmin.data);
    } catch (error) {
      console.log(error);
    }
  }, []);

  useEffect(() => {
    setLoading(true);
    fetchAdminStatus();

    const fetchDestinations = () => {
      fetch(`http://localhost:8080/api/destinations`)
        .then((response) => response.json())
        .then((data) => {
          setDestinations(data);
          setLoading(false);
        });
    };

    fetchAdminStatus();
    fetchDestinations();
  }, [fetchAdminStatus]);

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Container>
      <h1 style={{ marginTop: "65px" }}>All Destinations</h1>

      {loading && <CircularProgress />}

      {!loading && destinations.length == 0 && <div>No clients found</div>}

      {!loading && isAdmin && (
        <IconButton component={Link} sx={{ mr: 3 }} to={`/addDestinationAdmin`}>
          <Tooltip title="Add a new public destination" arrow>
            <PersonAddAlt1Icon
              style={{ color: "whitesmoke", fontSize: "50px" }}
            />
          </Tooltip>
        </IconButton>
      )}

      {!loading && !isAdmin && (
        <IconButton component={Link} sx={{ mr: 3 }} to={`/addDestination`}>
          <Tooltip title="Add a new public destination" arrow>
            <PersonAddAlt1Icon
              style={{ color: "whitesmoke", fontSize: "50px" }}
            />
          </Tooltip>
        </IconButton>
      )}

      {!loading && !isAdmin && (
        <Button
          component={Link}
          to={`/privatedestinations`}
          variant="contained"
          color="primary"
        >
          View Private List
        </Button>
      )}

      {!loading && destinations.length > 0 && (
        <>
          <TableContainer component={Paper}>
            <Table
              sx={{ minWidth: 1200 }}
              aria-label="table"
              style={{ backgroundColor: "whitesmoke" }}
            >
              <TableHead>
                <TableRow>
                  <TableCell
                    align="center"
                    style={{ color: "#2471A3", fontWeight: "bold" }}
                  >
                    #
                  </TableCell>
                  <TableCell
                    align="center"
                    style={{ color: "#2471A3", fontWeight: "bold" }}
                  >
                    Title
                  </TableCell>
                  <TableCell
                    align="center"
                    style={{ color: "#2471A3", fontWeight: "bold" }}
                  >
                    Geographical location
                  </TableCell>
                  <TableCell
                    align="center"
                    style={{ color: "#2471A3", fontWeight: "bold" }}
                  >
                    Description
                  </TableCell>
                  <TableCell
                    align="center"
                    style={{ color: "#2471A3", fontWeight: "bold" }}
                  >
                    Image
                  </TableCell>
                  <TableCell
                    align="center"
                    style={{ color: "#2471A3", fontWeight: "bold" }}
                  >
                    Operations
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {destinations.map((destination: Destination, index) => (
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
                    <TableCell align="center">
                      {destination.geo_location}
                    </TableCell>
                    <TableCell align="center">
                      {destination.description}
                    </TableCell>
                    <TableCell align="center" style={{ width: "40%" }}>
                      {
                        <img
                          src={destination.image}
                          alt="new"
                          style={{ width: "100%", height: "auto" }}
                        />
                      }
                    </TableCell>

                    <TableCell align="center">
                      <IconButton
                        component={Link}
                        sx={{ mr: 3 }}
                        to={`/destination/${destination.destinationId}/addprivate`}
                      >
                        <AddToPhotosIcon />
                      </IconButton>

                      <IconButton
                        component={Link}
                        sx={{ mr: 3 }}
                        to={`/destinations/${destination.destinationId}/delete`}
                      >
                        <DeleteForeverIcon sx={{ color: "red" }} />
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </>
      )}
    </Container>
  );
};
