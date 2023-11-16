import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import DirectionsCarIcon from '@mui/icons-material/DirectionsCar';

const pages = new Map<string, string>([
    ["Home", "/home"],
    ["Search User", "/search-user"],
    ["Search Car", "/search-car"]
]);

function prepareMenuButtons(){
    let result = [];
    for (let [key,value] of pages) {
        result.push(
            <Button key={key} href={value} sx={{ my: 2, color: 'white', display: 'block' }}>
            {key}
            </Button>
        )
    }
    return result
}

function HeaderMenu() {
    return (
        <AppBar position="static" id="headerMenu">
            <Container maxWidth="xl">
                <Toolbar disableGutters alignItems="center" justifyContent="center">
                    <DirectionsCarIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }}/>
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        sx={{
                            mr: 2,
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        USERS-CARS
                    </Typography>

                    <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                        <IconButton size="large" color="inherit">
                            <MenuIcon />
                        </IconButton>
                    </Box>
                    <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                        {prepareMenuButtons()}
                    </Box>
                </Toolbar>
            </Container>
        </AppBar>
    );
}
export default HeaderMenu;