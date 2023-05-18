import { Box, AppBar, Toolbar, IconButton, Typography, Button } from "@mui/material";
import { Link, useLocation, useNavigate } from "react-router-dom";
import SchoolIcon from "@mui/icons-material/School";


export const AppMenu = () => {
	const location = useLocation();
	const navigate = useNavigate();
	const path = location.pathname;
    const { loggedInUsername: username } = location.state;


	return (
        <Button sx={{ top: 0 ,backgroundColor: `rgba(170, 180, 230, 0.7)`, zIndex: 999,}}
          component={Link}
          to="/destinations"
          variant="contained"
          size="large"
          style={{
            position: 'fixed',
            top: 0,
            left: 0,
            margin: '10px',
          }}
        >
          Public Destinations
        </Button>
      );
};

