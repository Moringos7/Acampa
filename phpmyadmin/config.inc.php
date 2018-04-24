<?php
/* vim: set expandtab sw=4 ts=4 sts=4: */
/**
 * phpMyAdmin sample configuration, you can use it as base for
 * manual configuration. For easier setup you can use setup/
 *
 * All directives are explained in documentation in the doc/ folder
 * or at <http://docs.phpmyadmin.net/>.
 *
 * @package PhpMyAdmin
 */

/*
 * This is needed for cookie based authentication to encrypt password in
 * cookie
 */
$cfg['blowfish_secret'] = '828658b1861d891651'; /* You can put any random numbers/letters */

/*
 * Servers configuration
 */
$i = 0;


/*
 * First server
 */
$i++;
/* Authentication type */
/* Server parameters */
$cfg['Servers'][$i]['host'] = 'localhost';
$cfg['Servers'][$i]['user'] = 'root';
$cfg['Servers'][$i]['password'] = '123';
$cfg['Servers'][$i]['auth_type'] = 'config';

?>